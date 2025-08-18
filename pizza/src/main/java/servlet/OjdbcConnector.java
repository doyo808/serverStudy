package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class OjdbcConnector {
	private String url;
	private String user;
	private String pw;
	
	public OjdbcConnector(ServletContext context) {
		
		String driverPath = context.getInitParameter("driver_path");
		
		url = context.getInitParameter("jdbc_url");
		user = context.getInitParameter("jdbc_user");
		pw = context.getInitParameter("jdbc_password");
		
		try {
			Class.forName(driverPath);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnector() {
		try {
			return DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
