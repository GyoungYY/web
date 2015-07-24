package web;

public interface Processor {

	public boolean match(Request request);
	
	public void process(Request request, Response response)throws Exception;
	
	public void setFlag(boolean f);

	public void setBody(byte[] bytes);
}
