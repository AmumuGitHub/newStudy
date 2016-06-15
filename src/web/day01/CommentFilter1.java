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

public class CommentFilter1 implements Filter{
	private FilterConfig config;
	public CommentFilter1(){
		System.out.println(
				"CommentFilter1's constructor...");
	}
	/*
	 * 瀹瑰櫒鍦ㄥ垹闄よ繃婊ゅ櫒瀹炰緥涔嬪墠锛屼細璋冪敤
	 * destroy鏂规硶銆傝鏂规硶鍙細鎵ц涓�銆�
	 */
	public void destroy() {
		System.out.println("CommentFilter1's destroy...");
	}

	/*
	 * 瀹瑰櫒浼氳皟鐢╠oFilter鏂规硶鏉ュ鐞嗚姹�
	 * 鐩稿綋浜巗ervlet鐨剆ervice鏂规硶)銆�
	 * 瀹瑰櫒浼氬皢request瀵硅薄(arg0)鍜宺esponse瀵硅薄
	 * (arg1)浣滀负鍙傛暟浼犵粰doFilter鏂规硶銆�
	 * 濡傛灉璋冪敤浜咶ilterChain(arg2)鐨刣oFilter(request,
	 * response)鏂规硶锛屽垯瀹瑰櫒浼氳皟鐢ㄥ悗缁殑杩囨护鍣ㄦ垨鑰�
	 * servlet锛屽惁鍒欒姹傚鐞嗗畬姣曘�
	 * 
	 */
	public void doFilter(ServletRequest arg0, 
			ServletResponse arg1, FilterChain arg2) 
	throws IOException, ServletException {
		System.out.println("Filter1's doFilter begin...");
		HttpServletRequest request = 
			(HttpServletRequest)arg0;
		HttpServletResponse response = 
			(HttpServletResponse)arg1;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = 
			request.getParameter("content");
		String illegalStr = 
			config.getInitParameter("illegalStr");
		if(content.indexOf(illegalStr) != -1){
			//鏈夋晱鎰熷瓧
			out.println("<h1>璇勮鍐呭鍖呭惈浜嗘晱鎰熷瓧</h1>");
		}else{
			//娌℃湁鏁忔劅瀛�
			// FilterChain鐨刣oFilter鍙鎵ц锛屽鍣ㄥ氨浼�
			//璋冪敤鍚庣画鐨勮繃婊ゅ櫒鎴栬�servlet銆�
			arg2.doFilter(arg0, arg1);
		}
		System.out.println("Filter1's doFilter end.");
	}
	
	/*
	 * 瀹瑰櫒鍚姩涔嬪悗锛屼細鍒涘缓杩囨护鍣ㄥ疄渚嬨�
	 * 鎺ヤ笅鏉ワ紝瀹瑰櫒浼氳皟鐢ㄨ繃婊ゅ櫒鐨刬nit鏂规硶(瀹瑰櫒
	 * 浼氫簨鍏堝垱寤哄ソFilterConfig瀵硅薄)銆�
	 * FilterConfig瀵硅薄鍙互鐢ㄦ潵璁块棶杩囨护鍣ㄧ殑鍒濆
	 * 鍖栧弬鏁般�
	 * init鏂规硶鍙細鎵ц涓�銆�
	 */
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("CommentFilter1's init...");
		config = arg0;
	}

}
