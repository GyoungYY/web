package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * JDBC连接数据库以及对数据库的一些操作
 */
public class JDBCMysql {
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	public static final String URL = "jdbc:mysql://localhost:3306/userinfo";

	public static final String USER = "root";

	public static final String PASSWORD = "930206";

	public Connection connection = null;

	public Statement stmt = null;

	public JDBCMysql() throws Exception {
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		stmt = connection.createStatement();
	}

	public boolean isUser(User user) throws SQLException {
		if(user.getUsername()!=null){
		String sql = "select password from user where username ='" + user.getUsername() + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.next())
			return false;
		if (user.getPassword().equals(rs.getString("password"))) {
			return true;
		}
		else
			return false;
		}
		return false;
	}

	public boolean signUpUser(User user) throws SQLException {
		String sql = "select password from user where username = '" + user.getUsername() + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			return false;
		}
		sql = "insert into user values('" + user.getUsername() + "','" + user.getPassword() + "')";
		if (stmt.executeUpdate(sql) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ResultSet selectUser() throws SQLException{
		String sql="select * from user";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;     
	}
	
	public boolean deleteUser(User user) throws SQLException{
		String sql="delete from user where username='" + user.getUsername() + "'";
		if (stmt.executeUpdate(sql) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean modifyUser(User user) throws SQLException{
		String sql="update user set password='" + user.getPassword() + " where username='" + user.getUsername() + "'";
		if (stmt.executeUpdate(sql) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean increaseUser(User user) throws SQLException{
		String sql="insert into user values('" + user.getUsername() + "','" + user.getPassword() + "')";
		if (stmt.executeUpdate(sql) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
