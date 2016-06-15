package web.day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CommentServlet's service begin...");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = request.getParameter(
				"content");
		out.println("<h1>浣犵殑璇勮鏄�" + content 
				+ "</h1>");
		System.out.println("CommentServlet's service end.");
		out.close();
	}

}
