package web;
/*
 * 响应接口
 */
public interface Response {

	public static final int STATUS_200=200;
	public static final int STATUS_404=404;
	public static final int STATUS_503=503;

	public void setStatus(int status);
	
	public void setHeader(String key, String value);
	
	public void addBody(byte[] body);
	
	public void flush();
}
