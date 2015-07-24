package web;
/*
 * 各种动作的接口
 */
public interface Action {

	public String getUri();

	public void onGet(Request request, Response response);

	public void onPost(Request request, Response response);

}
