package shark.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JstlHomeProcess implements Process {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("홈으로?");
		return "redirect:/el_jstl/";
	}
}
