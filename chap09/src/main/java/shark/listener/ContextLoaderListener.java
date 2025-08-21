package shark.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 어플리케이션 이벤트이기 때문에 어플리케이션 객체에 접근이 가능하다
		ServletContext application = sce.getServletContext();
		
		System.out.println("DB 드라이버를 로딩했습니다...");
		System.out.println("DB 주소를 로딩했습니다..." + application.getInitParameter("db_url"));
		application.getInitParameter("db_url");
		
		System.out.println("서버가 로딩되는 중 입니다...");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("가지고 있던 Connection을 모두 터트리는 중...");
		
		System.out.println("서버가 종료되는 중 입니다...");
	}
}
