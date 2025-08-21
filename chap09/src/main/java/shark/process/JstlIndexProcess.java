package shark.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JstlIndexProcess implements Process {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		return "/el_jstl/index";
	}
	
}
