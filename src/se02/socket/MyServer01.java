package se02.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author amumu
 * @date 2016下午10:29:50
 */
public class MyServer01 {
	private ServerSocket server;
	private int port;
	private ExecutorService threadPool;

	public MyServer01(int port) {
		this.port = port;
		try {
			threadPool = Executors.newCachedThreadPool();
			server = new ServerSocket(port);
			System.out.println("启动服务器,申请端口号成功！！");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyServer01 server=new MyServer01(8088);
		server.start();
	}

	/**
	 * 该方法中开始与客户端进行交互工作
	 */
	private void start() {
		try {
			while(true){
				System.out.println("等待客户端连接..");
				Socket socket=server.accept();
				String ip = socket.getInetAddress().getHostAddress();
				System.out.println(ip+"连接了..");
				/**
				 * 当一个客户端连接上,我们启动一个线程
				 * 用来处理该客户端发送的信息
				 */
				Runnable handler = new GetClientInfoHandler(socket);
				threadPool.execute(handler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public class GetClientInfoHandler implements Runnable{
		//当前线程要处理的客户端的Socket
		private Socket socket;
		public GetClientInfoHandler(Socket socket){
			this.socket = socket;
		}
		public void run() {
			PrintWriter writer = null;
			try {
				/**
				 * 先通过该客户端的socket获取输出流
				 * 用于给该客户端发送信息
				 * 这里我们将该输出流放入共享的集合中.
				 */
				
				InputStream is= socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is,"UTF-8");
				BufferedReader reader=new BufferedReader(isr);
				while(true){
					String info = reader.readLine();
					System.out.println("客户端说:"+info);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
}
