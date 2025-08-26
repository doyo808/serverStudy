package jspboard.service.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.WebService;

public class Error404WebService extends WebService {

	private static Error404WebService instance = new Error404WebService();
	
	private Error404WebService() {};
	
	public static Error404WebService getInstance() {
		return instance;
	}

	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/views/error/404.jsp";
	}

}
