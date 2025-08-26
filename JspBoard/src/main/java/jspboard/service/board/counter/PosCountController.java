package jspboard.service.board.counter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.BoardDao;
import jspboard.dto.JspBoard;
import jspboard.service.WebService;

public class PosCountController extends WebService {

	private static PosCountController instance = new PosCountController();
	private PosCountController() {}
	public static PosCountController getInstance() {
		return instance;
	}
	
	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		// 여기서 좋아요 올려주고, 원래자리로 보낸다
		// 그러려면 글 아이디가 필요하다
		
		try {
			BoardDao dao = (BoardDao) req.getServletContext().getAttribute("boardDao");
			Integer board_id = Integer.parseInt(req.getParameter("board_id"));
			String ip = req.getParameter("ip");
			
			System.out.println("###\nboard_id: " + board_id);
			System.out.println("ip: " + ip);
			
			if (!dao.isAlreadyLiked(board_id, ip)) {
				System.out.println("좋아요 누른적 없는듯?\n###");
				dao.insertLikeHistory(board_id, ip);
				dao.IncreasePosCount(board_id);
			} else {
				System.out.println("좋아요 누른적 있는듯?\n###");
				dao.deleteLikeHistory(board_id, ip);
				dao.DecreasePosCount(board_id);
			}
			
			
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
