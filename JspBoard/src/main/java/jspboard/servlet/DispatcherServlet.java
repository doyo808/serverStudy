package jspboard.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.WebService;
import jspboard.service.board.BoardDetailService;
import jspboard.service.board.BoardListService;
import jspboard.service.board.BoardWriteFormService;
import jspboard.service.board.BoardWriteService;
import jspboard.service.board.counter.PosCountController;
import jspboard.service.error.Error404WebService;
import jspboard.service.main.MainIndexWebService;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// WebService라는 추상클래스를 이용하면 100% service메서드를 들고있어서 무조건 쓸 수 있다
	Map<String, WebService> cmd_mapping;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		
		cmd_mapping = new HashMap<>();
		cmd_mapping.put("GET:/", MainIndexWebService.getInstance());
		cmd_mapping.put("GET:/error/404", Error404WebService.getInstance());
		cmd_mapping.put("GET:/board/", BoardListService.getInstance(application));
		cmd_mapping.put("GET:/board/write", BoardWriteFormService.getInstance());
		cmd_mapping.put("POST:/board/write", BoardWriteService.getInstance());
		cmd_mapping.put("GET:/board/detail", BoardDetailService.getInstance());
		
		cmd_mapping.put("GET:/board/pos_count", PosCountController.getInstance());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("URI: " + req.getRequestURI());
		System.out.println("ContextPath: " + req.getContextPath());
		System.out.println("ServletPath: " + req.getServletPath());
		
		String cmd = req.getMethod() + ":" + req.getRequestURI();
		System.out.println("cmd: " + cmd);
		
		WebService webService = cmd_mapping.get(cmd);
		System.out.println("webService: " + webService);
		
		String nextView = null;
		
		if (webService != null) {
			nextView = webService.service(req, resp);
		}
		System.out.println("nextView: " + nextView);
		
		if (nextView != null) {
			if (nextView.startsWith("redirect:")) {
				resp.sendRedirect(nextView.replace("redirect:", ""));
			} else {
				req.getRequestDispatcher(nextView).forward(req, resp);
			}
		} else {
			resp.sendRedirect("/error/404");
		}
		
		System.out.println();
	}
}



