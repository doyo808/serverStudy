package jspboard.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.WebService;

public class BoardWriteFormService extends WebService {

	private static BoardWriteFormService instance = new BoardWriteFormService();
	
	private BoardWriteFormService() {}
	
	public static BoardWriteFormService getInstance() {
		return instance;
	}
	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/views/board/write.jsp";
	}
}
