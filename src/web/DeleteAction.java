package web;

public class DeleteAction  extends AbstractAction implements Action{
	public String getUri() {
		return "/delete.html";
	}
	
	public void onPost(Request request, Response response){
		//用户信息的删除
		Processor proc=new FileProcessor();
		JDBCMysql db=null;
		byte[] bytes=null;
		try{
			db=new JDBCMysql();
			if(db.deleteUser(request.getUserInfo())){
				String str="<html><body><h1>" + "Delete Successfully!" +"<br>"+ "</h1></body></html>";
				bytes=str.getBytes();
			}else {
				String str="<html><body><h1>" + "Delete Failed!" +"<br>"+ "</h1></body></html>";
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
