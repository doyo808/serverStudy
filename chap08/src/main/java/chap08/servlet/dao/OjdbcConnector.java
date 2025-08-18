package chap08.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OjdbcConnector {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String url;
	private static String user;
	private static String password;
	
	public OjdbcConnector(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
