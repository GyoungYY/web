package web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/*
 * 请求类，得到请求信息
 */
public class RequestImp implements Request {
	public String uri = null;
	public String method = null;
	public User user = null;
	public InputStream input;
	public String username=null;
	Map<String, String> map = new HashMap<String, String>();
	
	public RequestImp(InputStream in){
		input = in;
	}
	
	public void begin() throws IOException  {
		int i = 0, a = 0;
		byte[] bytes = this.getBytes(input);
		int length = bytes.length;
		while (i<length){
			StringBuffer sb = new StringBuffer();
			String str = null;
			while (true){
				a = bytes[i];
				i++;
				sb.append((char)a);
				if (a=='\n'||i==length)	break;
			}
			str = sb.toString();
			System.out.print(str);
			if (str.startsWith("GET")||str.startsWith("POST")){
				System.out.println("str="+str);
				String[] st = str.split(" ");
				uri = st[1];
				method = st[0];		
				map.clear();
			}else if (str.contains("password")&&"POST".equals(method)){
				String st[] = str.split("&");
				username = st[0].substring(st[0].indexOf("=")+1,st[0].length());
				String password = st[1].substring(st[1].indexOf("=")+1,st[1].length());
				user = new User(username, password);
			}else if (str.contains(":")){
				String[] st = str.split(" ", 2);
				st[0] = st[0].replace(":", "");
				st[1] = st[1].replace("\r\n", "");
				map.put(st[0], st[1]);
			}
		}
		if("POST".equals(method))
			System.out.print("\n\n");
	}
	
	private byte[] getBytes(InputStream input) throws IOException {
		int length = input.available();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
		try {
			byte[] buff = new byte[1024];
			while (input.available() > 0) {
				int size = input.read(buff);
				bos.write(buff, 0, size);
			}
			byte[] bytes = bos.toByteArray();
			return bytes;
		}
		finally {
			bos.close();
		}
	}

	public String getUri(){
		return uri;
	}
	
	public String getMethod(){
		return method;
	}
	
	public Map<String,String> getHeader(){
		return map;
	}
	
	public String getHead(String str){		
		
		return map.get(str);
	}
	
	public User getUserInfo(){
		return user;
	}
	
	public String getAdmin(){
		return username;
	}
}