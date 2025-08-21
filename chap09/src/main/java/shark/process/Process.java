package shark.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Process {
	
	// 처리라는 것은 request, response를 전달받아 하고싶은 일을 한 후
	// 가고싶은 주소를 리턴하는 것이다
	
	String process(HttpServletRequest request, HttpServletResponse response);
	
	
}
