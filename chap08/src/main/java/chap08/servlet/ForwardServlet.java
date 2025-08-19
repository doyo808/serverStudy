package chap08.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap08.dto.BurgerDTO;
import chap08.dto.DepartmentDTO;
import chap08.dto.EmployeeDto;
import chap08.servlet.dao.BurgerDAO;
import chap08.servlet.dao.DepartmentDAO;
import chap08.servlet.dao.EmployeeDao;
import chap08.servlet.dao.OjdbcConnector;

// ojdbc 빌드패스에 추가하고, deployment assembly에도 추가

public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		
		application.setAttribute("BURGER_PRICES"
				, new String[] {"2500", "3500", "5000", "5700", "6300"});
		
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
		
		// 1. 일단 uri를 확인 (어떤 DB에서 꺼낼지, 어디로 포워드 할지 결정됨)
		// 2. DB에서 데이터를 꺼냄 (하고 싶은 처리를 함, 비지니스 로직)
		// 3. 꺼낸 데이터를 실어놓음
		// 4. 포워드
		
		req.setCharacterEncoding("UTF-8");
		
		String method = req.getMethod();
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI().substring(contextPath.length());
		System.out.println("요청된 uri: " + uri);
		
		if (uri.equals("/employees/list")) {
			EmployeeDao employeeDao = new EmployeeDao(conn);
			List<EmployeeDto> empList = employeeDao.getAll();
			req.setAttribute("employees", empList);
			req.getRequestDispatcher("/WEB-INF/views/emp/emp_list.jsp").forward(req, resp);
		
		} else if (uri.equals("/departments/list")) {
			DepartmentDAO depDAO = new DepartmentDAO(conn);
			List<DepartmentDTO> depList = depDAO.getAllDeps();
			req.setAttribute("departments", depList);
			req.getRequestDispatcher("/WEB-INF/views/dep/dep_list.jsp").forward(req, resp);
		
		} else if (uri.equals("/burgers/list")) {
			BurgerDAO burgerDAO = new BurgerDAO(conn);
			List<BurgerDTO> burgerList = burgerDAO.getAllBurgers();
			req.setAttribute("burgers", burgerList);
			req.getRequestDispatcher("/WEB-INF/views/burger/burger_list.jsp").forward(req, resp);
		
		} else if (uri.equals("/burgers/add")) {
			if (method.equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/views/burger/add.jsp").forward(req, resp);				
			} else if (method.equals("POST")) {
				
				BurgerDAO burgerDAO = new BurgerDAO(conn);
				BurgerDTO burgerDTO = new BurgerDTO(
							// id가 있다면 null로 받고 시퀀스로 추가
							// 이전에 null체크를 반드시 수행해야한다!! (생략)
							req.getParameter("name"),
							Integer.parseInt(req.getParameter("price")),
							req.getParameter("taste"));
				burgerDAO.addBurger(burgerDTO);
				resp.sendRedirect(contextPath + "/burgers/list");
				
//				"사용자가 POST 요청 후 새로고침 시 같은 요청이 반복되어 
//				중복 데이터가 저장되는 문제를 방지하기 위해, 
//				PRG(Post-Redirect-Get) 패턴을 사용합니다. 
//				이는 POST 요청 처리 후 sendRedirect()로 
//				GET 요청을 유도함으로써 브라우저가 안전하게 새로고침할 수 있도록 합니다."
			} 
		
		} else if (uri.equals("/burgers/detail")) {
			BurgerDAO dao = new BurgerDAO(conn);
			
			String burger_name = req.getParameter("burger_name");
			BurgerDTO burger = dao.get(burger_name);
			
			req.setAttribute("burger", burger);
			req.getRequestDispatcher("/WEB-INF/views/burger/detail.jsp").forward(req, resp);
		
		} else if (uri.equals("/burgers/modify")) {
			if (method.equals("GET")) {
				BurgerDAO dao = new BurgerDAO(conn);
				
				String burger_name = req.getParameter("burger_name");
				BurgerDTO burger = dao.get(burger_name);
				req.setAttribute("burger", burger);
				req.getRequestDispatcher("/WEB-INF/views/burger/modify.jsp").forward(req, resp);				
			
			} else if (method.equals("POST")) {
				// 받은 정보를 DB에 반영하고 상세정보 페이지로 리다이렉트
				BurgerDAO dao = new BurgerDAO(conn);
				BurgerDTO dto = new BurgerDTO(
									req.getParameter("name"),
									Integer.parseInt(req.getParameter("price")),
									req.getParameter("taste"));
				dao.update(dto);
				
				String encoded_name = URLEncoder.encode(dto.getName(), "UTF-8");
				resp.sendRedirect(contextPath + "/burgers/detail?burger_name=" + encoded_name);
			}
		
		} else if (uri.equals("/burgers/delete")) {
			BurgerDAO dao = new BurgerDAO(conn);
			String burger_name = req.getParameter("burger_name");
			BurgerDTO burger = dao.get(burger_name);
			
			
			System.out.println("삭제할 버거:" + burger);
			
			int affectedRows = dao.delete(burger);
			if (affectedRows > 0) { System.out.println("삭제성공"); }
			else { System.out.println("삭제실패"); }
			
			resp.sendRedirect(contextPath + "/burgers/list");
			
			
		} else {
			resp.sendRedirect(contextPath + "/index.jsp");
		}
	
		
	}
	
	
	@Override
	public void destroy() {
		// 서블릿이 파괴될 때 (서버 내릴 때)
		try {
			if (conn != null) {
				conn.close();
				System.out.println("DB연결 해제!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
