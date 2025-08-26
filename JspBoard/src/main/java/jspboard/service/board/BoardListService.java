package jspboard.service.board;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.BoardDao;
import jspboard.service.WebService;

public class BoardListService extends WebService {

	private static BoardListService instance = new BoardListService();
	private BoardListService() {}
	private BoardDao boardDao;
	
	public static BoardListService getInstance(ServletContext application) {
		instance.boardDao = (BoardDao) application.getAttribute("boardDao");
		return instance;
	}
	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("boards", boardDao.selectAll());	
		return "/WEB-INF/views/board/list.jsp";
	}
	
}
