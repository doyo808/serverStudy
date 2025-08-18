package chap08.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap08.dto.DepartmentDTO;
import chap08.servlet.dao.DepartmentDAO;

public class ForwardServletDep extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			DepartmentDAO depDAO = new DepartmentDAO(dbConnector());
			List<DepartmentDTO> depList = depDAO.getAllDeps();
			req.setAttribute("departments", depList);
			req.getRequestDispatcher("/WEB-INF/views/dep/dep_list.jsp").forward(req, resp);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
	
	
	private static Connection dbConnector() throws ClassNotFoundException, SQLException {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
						  "jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "1234");
			return conn;
			
	}
}
