package web;
/*
 * 注册动作的执行
 */
public class RegistAction extends AbstractAction implements Action{

	public String getUri() {
		return "/signup.html";
	}

	public void onPost(Request request, Response response) {
		//注册的提交
		Processor proc=new FileProcessor();
		JDBCMysql db=null;
		byte[] bytes=null;
		try{
			db = new JDBCMysql();
			if (db.signUpUser(request.getUserInfo())){
				String str="<html><body><h1>" + "Rigist Successfully!" +"<br>"+ "</h1></body></html>";
				bytes=str.getBytes();
			}else {
				String str="<html><body><h1>" + "Rigist Failed!" +"<br>"+ "</h1></body></html>";
				bytes=str.getBytes();
			}
			proc.setFlag(false);
			proc.setBody(bytes);
			proc.process(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}