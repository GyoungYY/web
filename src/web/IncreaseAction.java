package web;

public class IncreaseAction extends AbstractAction implements Action{
	public String getUri() {
		return "/increase.html";
	}
	public void onPost(Request request, Response response){
		//用户信息的添加
		Processor proc=new FileProcessor();
		JDBCMysql db=null;
		byte[] bytes=null;
		try{
			db=new JDBCMysql();
			if(db.increaseUser(request.getUserInfo())){
				String str="<html><body><h1>" + "Increase Successfully!" +"<br>"+ "</h1></body></html>";
				bytes=str.getBytes();
			}else {
				String str="<html><body><h1>" + "Increase Failed!" +"<br>"+ "</h1></body></html>";
				bytes=str.getBytes();
			}
			proc.setFlag(false);
			proc.setBody(bytes);
			proc.process(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void onGet(Request request, Response response) {
		super.onGet(request, response);
	}
}
