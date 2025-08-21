package process;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import dto.PizzaDTO;

public class ToAddProcessor implements RequestProcessor {

	Connection conn;
	
	public ToAddProcessor(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp, String method) {
		if (method.equals("GET")) {
			return "/addPizza.jsp";
		
		} else if (method.equals("POST")) {
			PizzaDAO dao = new PizzaDAO(conn);
			
			LocalDate ld = LocalDate.parse(req.getParameter("made_date"));
			Date date = Date.valueOf(ld);
			PizzaDTO dto = new PizzaDTO( null,
										 req.getParameter("pizza_name"),
										 date);
			
			dao.addPizza(dto);
			return "redirect:/list";
			
		} else { return null; }
	}
 
}
