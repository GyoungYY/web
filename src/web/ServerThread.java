package web;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
/*
 * 线程类
 */
public class ServerThread extends Thread {
	private OutputStream os;
	private InputStream is;
	public Socket socket;
	public Map<String, Action> actions;
	
	public ServerThread(Socket sock,Map<String,Action> act) {
		socket = sock;
		actions=act;
	}

	public void run() {
		try {
			process(socket);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(Socket socket) throws Exception {
		os = socket.getOutputStream();
		is = socket.getInputStream();
		Request request = new RequestImp(is);
		Response response = new ResponseImp(os);
		Processor proc=new FileProcessor();
		request.begin();
		if(proc.match(request)){
			socket.close();
		}
		Action action=null;
		String uri=request.getUri();
		if(actions.containsKey(uri)){
			action=actions.get(uri);
		}else{
			action=new AllAction();
		}
		if("GET".equals(request.getMethod())){
			action.onGet(request, response);
		}else if ("POST".equals(request.getMethod())){
			action.onPost(request, response);
		}
		socket.close();
	}
}
