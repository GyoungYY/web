package web;
/*
 * 用户信息类
 */
public class User {
	public String username;

	public String password;

	public User(String user, String pasd) {
		this.username=user;
		this.password=pasd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasd) {
		password = pasd;
	}
}
