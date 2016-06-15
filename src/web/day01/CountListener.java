package web.day01;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements 
HttpSessionListener{
	private int count = 0;
	public void sessionCreated(HttpSessionEvent arg0) {
		count ++;
		System.out.println("sessionCreated...");
		HttpSession session = arg0.getSession();
		ServletContext ctx = session.getServletContext();
		//将人数绑订到servlet上下文，方便其它
		//组件访问。
		ctx.setAttribute("count", count);
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		count --;
		System.out.println("sessionDestroyed...");
		HttpSession session = arg0.getSession();
		ServletContext ctx = session.getServletContext();
		//将人数绑订到servlet上下文，方便其它
		//组件访问。
		ctx.setAttribute("count", count);
	}

}
