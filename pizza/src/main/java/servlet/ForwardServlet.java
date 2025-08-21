package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.RequestProcessor;
import process.ToAddProcessor;
import process.ToDeleteAllProcessor;
import process.ToDeleteProcessor;
import process.ToDetailProcessor;
import process.ToListProcessor;
import process.ToModifyProcessor;

public class ForwardServlet extends HttpServlet {
	
	private Connection conn;
	private String jspFolderPath;
	private Map<String, RequestProcessor> uriMapper = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		OjdbcConnector connector = new OjdbcConnector(getServletContext());
		conn = connector.getConnector();
		jspFolderPath = "/WEB-INF/views/pizza";
		
		uriMapper.put("/list", new ToListProcessor(conn));
		uriMapper.put("/add", new ToAddProcessor(conn));
		uriMapper.put("/detail", new ToDetailProcessor(conn));
		uriMapper.put("/modify", new ToModifyProcessor(conn));
		uriMapper.put("/delete", new ToDeleteProcessor(conn));
		uriMapper.put("/deleteAll", new ToDeleteAllProcessor(conn));
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String contextPath = req.getContextPath();
		String method = req.getMethod();
		String uri = req.getRequestURI().substring(contextPath.length());
		System.out.println("요청된 uri: " + uri);
		
		
		RequestProcessor rp = uriMapper.get(uri);
		
		if (rp != null) {
			String nextServlet = rp.process(req, resp, method);
			System.out.println("다음갈곳: " + nextServlet);
			
			if (nextServlet.startsWith("redirect:")) {
				resp.sendRedirect(nextServlet.replace("redirect:", contextPath));
			} else {
				req.getRequestDispatcher(jspFolderPath + nextServlet).forward(req, resp);
			}
			
		} else {
			resp.sendRedirect(contextPath + "/index.jsp");
		}
	
		
		
	}
	
	@Override
	public void destroy() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("DB 연결 해제");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
