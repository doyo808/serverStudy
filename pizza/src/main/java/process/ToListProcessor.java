package process;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import dto.PizzaDTO;

public class ToListProcessor implements RequestProcessor {

	Connection conn;
	public ToListProcessor(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp, String method) {
		PizzaDAO pizzaDAO = new PizzaDAO(conn);
		List<PizzaDTO> pizzas = pizzaDAO.getAllPizzas();
		req.setAttribute("pizzas", pizzas);
		
		return "/pizza_list.jsp";
	}

}
