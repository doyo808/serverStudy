package jspboard.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.BoardDao;
import jspboard.dto.JspBoard;
import jspboard.service.WebService;

public class BoardDetailService extends WebService {

	private static BoardDetailService instance = new BoardDetailService();
	private BoardDetailService() {}
	public static BoardDetailService getInstance() {
		return instance;
	}
	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer board_id = Integer.parseInt(req.getParameter("board_id"));
			
			BoardDao dao = (BoardDao) req.getServletContext().getAttribute("boardDao");

			// 조회수를 늘려준다
			dao.increaseViewCount(board_id);
			// 조회를 수행한다
			JspBoard board = dao.selectOne(board_id);
			
			if (board == null) {
				throw new NumberFormatException("글 번호로 조회 실패!!");
			} else {
				req.setAttribute("board", board);
				return "/WEB-INF/views/board/detail.jsp";
			}
			
			
		} catch (NumberFormatException e) {
			return "/WEB-INF/views/error/board_not_exist.jsp";
		}
	}

}
