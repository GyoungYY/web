package web;

import java.io.File;
import java.sql.ResultSet;
/*
 * 登录动作的执行，包括管理员登录，普通账户登录和输入的用户名或者密码错误
 */
public class LoginAction extends AbstractAction implements Action {

	public String getUri() {
		return "/login.html";
	}

	public void onPost(Request request, Response response) {
		Processor proc=new FileProcessor();
		JDBCMysql db=null;
		byte[] bytes=null;
		try {
			db = new JDBCMysql();
			if(db.isUser(request.getUserInfo())&&"yangguang".equals(request.getAdmin())){
				ResultSet rs=db.selectUser();
				String str="";
				File f=new File("D:/webroot/modify.html");
				File f1=new File("D:/webroot/delete.html");
				File f2=new File("D:/webroot/increase.html");
				str="<html><body><h1>" + "Admin in logging!" +"<br>"+"</h1>"+"Users list:<br>";
				while(rs.next()){
					str+=rs.getString("username")+":  "+rs.getString("password")+"<br>";
				}
				str+="<br><br><br>You can modify users:";
				str+="<a href='" + f.getName() + "'>";
				str+=f.getName()+"<br>";
				str+="<br>You can delete users:";
				str+="<a href='" + f1.getName() + "'>";
				str+=f1.getName()+"<br>";
				str+="<br>You can increase users:";
				str+="<a href='" + f2.getName() + "'>";
				str+=f2.getName()+"<br>";
				str+="</body></html>";
				bytes=str.getBytes();
			}
			else if (db.isUser(request.getUserInfo())){
				File f = new File("D:/webroot");
				String[] files = f.list();
				StringBuffer sb = new StringBuffer("");
				sb.append("<html><body><h1>" + "You're already in the state of logging!" + "</h1>");
				for (int i = 0; i < files.length; i++) {
					sb.append("<a href='" + files[i] + "'>");
					sb.append(files[i]+"<br>");
				}
				sb.append("</body></html>");
				bytes = sb.toString().getBytes();
			}else {
				String str = "Username or Password error!";
				bytes = str.getBytes();
			} 
			proc.setFlag(false);
			proc.setBody(bytes);
			proc.process(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void onGet(Request request, Response response) {
		super.onGet(request, response);
	}
}