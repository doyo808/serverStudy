package shark.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterIndexProcess implements Process {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		return "/filter/index";
	}
	
}
