package jspboard.service.board;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.BoardDao;
import jspboard.service.WebService;

public class BoardWriteService extends WebService {
	
	private static BoardWriteService instance = new BoardWriteService();
	private BoardWriteService() {}
	
	public static BoardWriteService getInstance() {
		return instance;
	}
	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		String board_title = req.getParameter("board_title");
		String board_writer = req.getParameter("board_writer");
		String board_writer_ip_addr = req.getRemoteAddr();
		String board_password = req.getParameter("board_password");
		String board_content = req.getParameter("board_content");
		
		if (board_title == "" || board_title == null) {
			req.setAttribute("cause", "글 제목");
		} else if (board_writer == "" || board_writer== null) {
			req.setAttribute("cause", "작성자");
		} else if (board_password == "" || board_password == null) {
			req.setAttribute("cause", "글 비밀번호");
		} else if (board_content =="" || board_content == null) {
			req.setAttribute("cause", "글 내용");
		} else {
			// DB에 글 쓰기
			ServletContext application = req.getServletContext();
			BoardDao dao = (BoardDao)application.getAttribute("boardDao");
			
			dao.insert(board_title, board_writer, board_writer_ip_addr, board_password, board_content);
			
			
			return "redirect:/board/";
		}
		
		
		
		return "/WEB-INF/views/board/write.jsp";
	}
}
