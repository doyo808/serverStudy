package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import dto.PizzaDTO;

public class ForwardServlet extends HttpServlet {
	
	private static Connection conn;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		OjdbcConnector connector = new OjdbcConnector(getServletContext());
		conn = connector.getConnector();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String contextPath = req.getContextPath();
		String jspFolderPath = "WEB-INF/views/pizza";
		String method = req.getMethod();
		String uri = req.getRequestURI().substring(contextPath.length());
		System.out.println("요청된 uri: " + uri);
		
		if (uri.equals("/list")) {
			
			PizzaDAO pizzaDAO = new PizzaDAO(conn);
			List<PizzaDTO> pizzas = pizzaDAO.getAllPizzas();
			req.setAttribute("pizzas", pizzas);
			req.getRequestDispatcher(jspFolderPath + "/pizza_list.jsp").forward(req, resp);
			
		} else if (uri.equals("/add")) {
			
			if (method.equals("GET")) {
				req.getRequestDispatcher(jspFolderPath + "/addPizza.jsp").forward(req, resp);
			} else if (method.equals("POST")) {
				PizzaDAO dao = new PizzaDAO(conn);
				
				LocalDate ld = LocalDate.parse(req.getParameter("made_date"));
				Date date = Date.valueOf(ld);
				PizzaDTO dto = new PizzaDTO( null,
											 req.getParameter("pizza_name"),
											 date);
				
				dao.addPizza(dto);
				resp.sendRedirect("./list");
			}
		
		} else if (uri.equals("/detail")) {
			
			PizzaDAO dao = new PizzaDAO(conn);
			int id = Integer.parseInt(req.getParameter("pizza_id"));
			PizzaDTO dto = dao.getPizza(id);
			
			req.setAttribute("pizza", dto);
			req.getRequestDispatcher(jspFolderPath + "/pizza_detail.jsp").forward(req, resp);
		
		} else if (uri.equals("/modify")) {
			PizzaDAO dao = new PizzaDAO(conn);
			
			int id = Integer.parseInt(req.getParameter("pizza_id"));
			String name = req.getParameter("pizza_name");
			Date date = Date.valueOf(LocalDate.parse(req.getParameter("made_date")));
			
			PizzaDTO pizza = new PizzaDTO(id, name, date);
			dao.updatePizza(pizza);
			
			resp.sendRedirect("./list");
		
		} else if (uri.equals("/delete")) {
			PizzaDAO dao = new PizzaDAO(conn);
			int id = Integer.parseInt(req.getParameter("pizza_id"));
			
			dao.deletePizzaById(id);
			resp.sendRedirect("./list");
		
		} else if (uri.equals("/deleteAll")) {
			PizzaDAO dao = new PizzaDAO(conn);
			Date date = Date.valueOf( LocalDate.parse( req.getParameter("made_date") ) );
			
			int deletedRows = dao.deletePizzasByDate(date);
			
			List<PizzaDTO> pizzas = dao.getAllPizzas();
			req.setAttribute("pizzas", pizzas);
			req.setAttribute("rows", deletedRows);
			req.getRequestDispatcher(jspFolderPath + "/pizza_list.jsp").forward(req, resp);
			
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
