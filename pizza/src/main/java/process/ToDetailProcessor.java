package process;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import dto.PizzaDTO;

public class ToDetailProcessor implements RequestProcessor {

	Connection conn;
	public ToDetailProcessor(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp, String method) {
		PizzaDAO dao = new PizzaDAO(conn);
		int id = Integer.parseInt(req.getParameter("pizza_id"));
		PizzaDTO dto = dao.getPizza(id);
		
		req.setAttribute("pizza", dto);
		return "/pizza_detail.jsp";
	}

}
