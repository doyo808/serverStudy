package chap08.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap08.dto.EmployeeDto;
import chap08.servlet.dao.EmployeeDao;
import chap08.servlet.dao.OjdbcConnector;

// ojdbc 빌드패스에 추가하고, deployment assembly에도 추가

public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		try {
			conn = new OjdbcConnector(
					application.getInitParameter("jdbc_url"),
					application.getInitParameter("jdbc_user"),
					application.getInitParameter("jdbc_password")
					).getConnection();
		} catch (SQLException e) {
			conn = null;
			e.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이곳에 접속하면 DB로부터 모든 사원들의 정보를 꺼내서 콘솔에 출력해보세요
		
		EmployeeDao employeeDao = new EmployeeDao(conn);
		List<EmployeeDto> empList = employeeDao.getAll();
		System.out.println(empList);
		req.setAttribute("employees", empList);
		req.getRequestDispatcher("/WEB-INF/views/emp/emp_list.jsp").forward(req, resp);
		
		
//		String sql = "SELECT * FROM employees";
//		try (PreparedStatement pstmt = dbConnector().prepareStatement(sql);
//				ResultSet rs = pstmt.executeQuery()) 
//		{
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			
//			String[] columnLables = new String[columnCount];
//			String[] columnTypeNames = new String[columnCount];
//			
//			for (int i = 1; i <= columnCount; i++) {
//				columnLables[i-1] = metaData.getColumnLabel(i);
//				columnTypeNames[i-1] = metaData.getColumnTypeName(i);
//			}
//			
//			while (rs.next()) {
//				for (int i = 0; i < columnCount; i++) {
//					System.out.printf("[%s: %s]" + (i == columnCount-1 ? "" : " & "),
//							columnLables[i] , rs.getString(columnLables[i]));
//				}
//				System.out.println();
//			}
//			
//			
//			
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
	
	
	
//	private static Connection dbConnector() throws ClassNotFoundException, SQLException {
//		
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection conn = DriverManager.getConnection(
//						  "jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "1234");
//			return conn;
//			
//	}
	
	
	
}
