package shark.process;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shark.dto.Book;

public class ElTestProcess implements Process {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) {
		req.getServletContext().setAttribute("book", "[디지털디자인] 모바일 UI/UX 반응형 웹디자인");
		req.getSession().setAttribute("book", "모두의 HTML5 % CSS3");
		req.setAttribute("book",  "포토샵 & 일러스트레이터 2022");
		
		req.setAttribute("obj_book", 
				new Book(  "9791170712162",
							"손자병법",
							"임용한",
							22800,
							500,
							java.sql.Date.valueOf(LocalDate.of(2025, 1, 28))) );
		
		req.setAttribute("score", 2222);
		req.setAttribute("book", "스타크래프트 잘하는법");
		req.setAttribute("CLOTHES_SIZES", new String[] {"XL", "L", "S", "XS"});
		
		return "/el_jstl/el";
	}

}
