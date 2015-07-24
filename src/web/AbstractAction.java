package web;
/*
 * 一个实现Action接口的抽象类，实现了文件处理的功能，该抽象类的子类的实例可以调用该功能函数
 */
public abstract class AbstractAction implements Action{

	public void onGet(Request request, Response response) {
		//TODO 文件处理
		try {
			FileProcessor proc =new FileProcessor();
			proc.setFlag(true);
			proc.process(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onPost(Request request, Response response) {
		
	}
}