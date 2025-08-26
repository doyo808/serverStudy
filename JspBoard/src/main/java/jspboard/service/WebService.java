package jspboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class WebService {
	public abstract String service(HttpServletRequest req, HttpServletResponse resp);
}
