package process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestProcessor {
	String process(HttpServletRequest req, HttpServletResponse resp, String method);
}
