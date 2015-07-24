package web;

import java.io.IOException;
import java.util.Map;
/*
 * 请求接口
 */
public interface Request {
	public void begin() throws IOException;

	public String getUri();
	
	public String getMethod();
	
	public Map<String,String> getHeader();
	
	public String getHead(String str);
	
	public User getUserInfo();
	
	public String getAdmin();
}
