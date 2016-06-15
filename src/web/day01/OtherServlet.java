package web.day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OtherServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext sctx = 
			getServletContext();
		String name = (String) sctx.getAttribute("name");
//		HttpSession session = 
//			request.getSession();
//		String name = (String)session.getAttribute("name");
		out.println(name);
		String company = 
			sctx.getInitParameter("company");
		out.println(company);
		out.close();
	}

}
