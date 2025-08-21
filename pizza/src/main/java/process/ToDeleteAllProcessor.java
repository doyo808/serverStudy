package process;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import dto.PizzaDTO;

public class ToDeleteAllProcessor implements RequestProcessor {
	
	Connection conn;
	public ToDeleteAllProcessor(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp, String method) {
		PizzaDAO dao = new PizzaDAO(conn);
		Date date = Date.valueOf( LocalDate.parse( req.getParameter("made_date") ) );
		
		int deletedRows = dao.deletePizzasByDate(date);
		
		List<PizzaDTO> pizzas = dao.getAllPizzas();
		req.setAttribute("pizzas", pizzas);
		req.setAttribute("rows", deletedRows);
		
		return "/pizza_list.jsp";
	}

}
