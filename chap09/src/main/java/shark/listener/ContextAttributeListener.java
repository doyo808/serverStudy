package shark.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class ContextAttributeListener implements ServletContextAttributeListener {
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("application 객체에 어트리뷰트 추가하는 것을 감지했습니다.");
		ServletContext application = event.getServletContext();
		
		if (application.getAttribute("iceCream").toString().length() < 5) {
			application.setAttribute("iceCream", "메로나 망고맛");
			System.out.println("아이스크림 이름을 변경했습니다.");
		}
	}
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("어트리뷰트가 삭제되면 발생하는 이벤트...");
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("어트리뷰트가 수정되면 발생하는 이벤트...");
		
		ServletContext application = event.getServletContext();
		
		if (application.getAttribute("iceCream").toString().length() < 5) {
			application.setAttribute("iceCream", "베리베리 스트로베리");
			System.out.println("아이스크림 이름을 변경했습니다.");
		}
	}

}
