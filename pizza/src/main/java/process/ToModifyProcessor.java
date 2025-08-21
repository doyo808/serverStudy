package process;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import dto.PizzaDTO;

public class ToModifyProcessor implements RequestProcessor {
	
	Connection conn;
	public ToModifyProcessor(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp, String method) {
		PizzaDAO dao = new PizzaDAO(conn);
		
		int id = Integer.parseInt(req.getParameter("pizza_id"));
		String name = req.getParameter("pizza_name");
		Date date = Date.valueOf(LocalDate.parse(req.getParameter("made_date")));
		
		PizzaDTO pizza = new PizzaDTO(id, name, date);
		dao.updatePizza(pizza);
		
		return "redirect:/list";
	}
}
