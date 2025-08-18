package servlet;

import java.io.IOException;
import java.sql.Connection;
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
		
		resp.setCharacterEncoding("UTF-8");
		
		String method = req.getMethod();
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI().substring(contextPath.length());
		
		if (uri.equals("/list")) {
			
			PizzaDAO pizzaDAO = new PizzaDAO(conn);
			List<PizzaDTO> pizzas = pizzaDAO.getAllPizzas();
			req.setAttribute("pizzas", pizzas);
			req.getRequestDispatcher("/WEB-INF/views/pizza/pizza_list.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect(contextPath + "/index.jsp");
		}
		
		
		
		
	}
}
