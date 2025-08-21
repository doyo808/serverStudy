package shark.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter1 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터1 들렸다 감!");
		request.setAttribute("my_value", "필터에 들렸다 왔다는 증거");
		
		// 다음으로 가야할 곳으로 보내준다 (필터 또는 서블릿)
		chain.doFilter(request, response);
	}
	
}
