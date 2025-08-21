package shark.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shark.process.ElTestProcess;
import shark.process.FilterIndexProcess;
import shark.process.JstlHomeProcess;
import shark.process.JstlIndexProcess;
import shark.process.JstlTestProcess;
import shark.process.ListenerIndexProcess;
import shark.process.Process;

public class FrontController extends HttpServlet {
	
	private String encoding;
	private String prefix = "/WEB-INF/views";
	private String suffix = ".jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.encoding = config.getInitParameter("encoding");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(encoding);
		
		// ServletContext: 서블릿들 담아놓는 통(서버)
		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String ServletPath = req.getServletPath(); // 이 서블릿으로 들어오기 위해 사용했던 주소
		String cmd = req.getRequestURI().substring(contextPath.length());
		System.out.println("uri: " + uri);
		System.out.println("ServletPath: " + ServletPath);
		System.out.println("contextPath: " + contextPath);
		System.out.println("command: " + cmd);
		
		// 1. 하고싶은 처리(Service, Business Logic)을 한다
		// 2. 어느 페이지를 사용자에게 보여줄지 정한다(포워드 또는 리다이렉트)
		
		Map<String, Process> cmd_mapper = new HashMap<>();
		cmd_mapper.put("/el_jstl/", new JstlIndexProcess());
		cmd_mapper.put("/el_jstl/jstl", new JstlTestProcess());
		cmd_mapper.put("/el_jstl/el", new ElTestProcess());
		cmd_mapper.put("/el_jstl/home", new JstlHomeProcess());
		cmd_mapper.put("/filter/", new FilterIndexProcess());
		cmd_mapper.put("/listener/", new ListenerIndexProcess());
		
		Process service = cmd_mapper.get(cmd);
		
		if (service != null) {
			String nextServlet = service.process(req, resp);
			
			if (nextServlet.startsWith("redirect:")) {
				String redirectPath = nextServlet.substring("redirect:".length());
		        System.out.println("리다이렉트할게 → " + redirectPath);
		        resp.sendRedirect(req.getContextPath() + redirectPath);
				
			} else {
				req.getRequestDispatcher(prefix + nextServlet + suffix).forward(req, resp);
				System.out.println("목표주소: " + prefix + nextServlet + suffix);
			}
		} else {
			throw new ServletException("올바르지 않은 URI입니다...");
		}
		
		
		
		
		// 다형성을 활용하여 너무 길어질 수 있는 if~else if 문을 없애버릴 수 있다
//		if (todo.equals("/el_jstl/")) {
//			req.getRequestDispatcher("/WEB-INF/views/el_jstl/index.jsp").forward(req, resp);
//			
//		} else if (todo.equals("/el_jstl/el")) {
//			application.setAttribute("book", "[디지털디자인] 모바일 UI/UX 반응형 웹디자인");
//			session.setAttribute("book", "모두의 HTML5 % CSS3");
//			req.setAttribute("book",  "포토샵 & 일러스트레이터 2022");
//			
//			req.setAttribute("obj_book", 
//					new Book(  "9791170712162",
//								"손자병법",
//								"임용한",
//								22800,
//								500,
//								java.sql.Date.valueOf(LocalDate.of(2025, 1, 28))) );
//			
//			req.setAttribute("score", 2222);
//			req.setAttribute("book", "스타크래프트 잘하는법");
//			req.setAttribute("CLOTHES_SIZES", new String[] {"XL", "L", "S", "XS"});
//			
//			req.getRequestDispatcher("/WEB-INF/views/el_jstl/el.jsp").forward(req, resp);
//			
//		} else if (todo.equals("/el_jstl/jstl")) {
//			List<Book> steadySellers = new ArrayList<>();
//			
//			req.setAttribute("steadies", steadySellers);
//			
//			Date d = java.sql.Date.valueOf(LocalDate.of(2025, 1, 28));
//			steadySellers.add(new Book("1", "그리스 로마 신화", "남궁성", 3000, 100, d));
//			steadySellers.add(new Book("2", "죄와벌", "남궁성", 3000, 100, d));
//			steadySellers.add(new Book("3", "연금술사", "남궁성", 3000, 100, d));
//			steadySellers.add(new Book("4", "모비딕", "남궁성", 3000, 100, d));
//			
//			req.getRequestDispatcher("/WEB-INF/views/el_jstl/jstl.jsp").forward(req, resp);
//			
//		}
		
		
	}
}
