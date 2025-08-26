package jspboard.service.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.WebService;

public class MainIndexWebService extends WebService {
	
	// 이 클래스의 인스턴스가 하나만 존재하게 되는 것을 보증하는 코드 (싱글톤 디자인패턴)
	private static MainIndexWebService instance = new MainIndexWebService();
	
	private MainIndexWebService() {}
	
	public static MainIndexWebService getInstance() {
		return instance;
	}
	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/views/main/index.jsp";
	}

}
