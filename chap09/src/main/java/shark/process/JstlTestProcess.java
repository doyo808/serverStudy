package shark.process;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shark.dto.Book;

public class JstlTestProcess implements Process {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) {
		List<Book> steadySellers = new ArrayList<>();
		
		req.setAttribute("steadies", steadySellers);
		
		Date d = java.sql.Date.valueOf(LocalDate.of(2025, 1, 28));
		steadySellers.add(new Book("1", "그리스 로마 신화", "남궁성", 3000, 100, d));
		steadySellers.add(new Book("2", "죄와벌", "남궁성", 3000, 100, d));
		steadySellers.add(new Book("3", "연금술사", "남궁성", 3000, 100, d));
		steadySellers.add(new Book("4", "모비딕", "남궁성", 3000, 100, d));
		
		return "/el_jstl/jstl";
	}

}
