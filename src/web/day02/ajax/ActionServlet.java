package web.day02.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		String action = 
			uri.substring(uri.lastIndexOf("/"),
					uri.lastIndexOf("."));
		if(action.equals("/quoto")){
			//模拟生成几支股票的信息
			Random r = new Random();
			List<Stock> stocks = 
				new ArrayList<Stock>();
			for(int i=0;i<8;i++){
				Stock s = new Stock();
				s.setCode("60001" +r.nextInt(10));
				s.setName("山东高速" + r.nextInt(10));
				s.setPrice(100 * r.nextDouble());
				stocks.add(s);
			}
			JSONArray jsonArr = 
				JSONArray.fromObject(stocks);
			String jsonStr = jsonArr.toString();
			System.out.println(jsonStr);
			out.println(jsonStr);
		}else if(action.equals("/getNumber")){
			Random r = new Random();
			int number = r.nextInt(1000);
			System.out.println(number);
			out.println(number);
		}else if(action.equals("/check_username")){
//			try {
//				Thread.sleep(6000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			String username = 
				request.getParameter("username");
			System.out.println("username:" + username);
			if("李白".equals(username)){
				out.print("error");
			}else{
				out.print("ok");
			}
		}else if(action.equals("/getCity")){
			String name = request.getParameter("name");
			if("bj".equals(name)){
				out.println("朝阳,cy;东城,dc");
			}else if("hb".equals(name)){
				out.println("石家庄,sjz;唐山,ts;邢台,xt");
			}else{
				out.println("青岛,qd;济南,jn");
			}
		}
		out.close();
	}

}
