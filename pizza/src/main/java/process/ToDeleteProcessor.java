package process;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;

public class ToDeleteProcessor implements RequestProcessor {
	
	Connection conn;
	public ToDeleteProcessor(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp, String method) {
		PizzaDAO dao = new PizzaDAO(conn);
		int id = Integer.parseInt(req.getParameter("pizza_id"));
		
		dao.deletePizzaById(id);
		return "redirect:/list";
	}

}
