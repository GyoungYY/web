package web;

public class ModifyAction extends AbstractAction implements Action {
	public String getUri() {
		return "/modify.html";
	}
	
	public void onPost(Request request, Response response){
		//用户信息的修改
		Processor proc=new FileProcessor();
		JDBCMysql db=null;
		byte[] bytes=null;
		try{
			db=new JDBCMysql();
			if(db.modifyUser(request.getUserInfo())){
				String str="<html><body><h1>" + "Modify Successfully!" +"<br>"+ "</h1></body></html>";
				bytes=str.getBytes();
			}else {
				String str="<html><body><h1>" + "Modify Failed!" +"<br>"+ "</h1></body></html>";
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
