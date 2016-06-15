package web.day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter2 implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, 
			ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		System.out.println("Filter2's doFilter begin...");
		HttpServletRequest request = 
			(HttpServletRequest)arg0;
		HttpServletResponse response = 
			(HttpServletResponse)arg1;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = request.getParameter(
				"content");
		if(content.length() > 20){
			out.println("<h1>璇勮鐨勫瓧鏁拌繃澶�/h1>");
		}else{
			arg2.doFilter(arg0, arg1);
		}
		System.out.println("Filter2's doFilter end.");
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
