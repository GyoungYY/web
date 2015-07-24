package web;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * <p>Copyright: Copyright (c) 2015<p>
 * <p>succez<p>
 * @author YANGGUANG
 * @createdate 2015年7月24日
 */
public class Server {
	public static void main(String[] args) throws IOException {
		Action login = new LoginAction();
		Action regist = new RegistAction();
		Action modify = new ModifyAction();
		Action delete = new DeleteAction();
		Action increase = new IncreaseAction();
		Map<String, Action> actions = new HashMap<String,Action>(5);
		actions.put(login.getUri(), login);
		actions.put(regist.getUri(), regist);
		actions.put(modify.getUri(),modify);
		actions.put(delete.getUri(),delete);
		actions.put(increase.getUri(),increase);
		ServerSocket serverSocket = new ServerSocket(8080,1,InetAddress.getByName("127.0.0.1"));
		ExecutorService pool = Executors.newCachedThreadPool();
		while (true) {
			Socket socket = serverSocket.accept();
			ServerThread st = new ServerThread(socket,actions);
			pool.execute(st);
		}
	}
}
