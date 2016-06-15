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
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 服务端
 * @author Administrator
 *
 */
public class Server {
	//服务端的Socket
	private ServerSocket server;
	
	//保存所有客户端输出流的集合
	private List<PrintWriter> allOut;
	
	//线程池
	private ExecutorService threadPool;
	
	//消息队列
	private BlockingDeque<String> messageQueue;
	
	public Server(){
		try {
			//初始化消息队列
			messageQueue = new LinkedBlockingDeque<String>();
			
			//初始化线程池
			threadPool = Executors.newCachedThreadPool();
//			threadPool = Executors.newFixedThreadPool(50);
			
			//应创建线程安全的集合
			allOut = new Vector<PrintWriter>();
		
			/**
			 * 创建服务端Socket,并向操作系统申请打开一个
			 * 服务端口
			 */
			System.out.println("启动服务器,申请端口号");
			server = new ServerSocket(8088);
			System.out.println("端口申请完毕");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		try {
			/**
			 * 将转发信息的线程启动
			 */
			SendMessageToAllClientHandler sendHandler= new SendMessageToAllClientHandler();
			Thread t = new Thread(sendHandler);
			t.setDaemon(true);
			t.start();
			
			
			/**
			 * accept()方法是一个阻塞方法
			 * 该方法会监听8088端口,直到一个客户端的连接
			 * 当客户端连接后,将该客户端的Socket返回
			 */
			while(true){
				System.out.println("等待客户端连接...");
				Socket socket = server.accept();
				//获取该客户端的ip地址
				String ip = 
					socket.getInetAddress().getHostAddress();
//				String host =
//					socket.getInetAddress().getHostName();			
				System.out.println(ip+"连接了!");
				/**
				 * 当一个客户端连接上,我们启动一个线程
				 * 用来处理该客户端发送的信息
				 */
				Runnable handler 
								 = new GetClientInfoHandler(socket);
//				Thread t = new Thread(handler);
//				t.start();//启动线程
				
				//将任务指派给线程池,使其分配空线程来运行
				threadPool.execute(handler);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将给定的输出流存入共享集合
	 */
	public synchronized void addOut(PrintWriter writer){
		allOut.add(writer);
	}
	/**
	 * 从共享集合中删除给定的输出流
	 * @param writer
	 */
	public synchronized void removeOut(PrintWriter writer){
		allOut.remove(writer);
	}	
	/**
	 * 广播信息
	 * 将给定的信息发送给所有的客户端
	 * @param message
	 */
	public synchronized void sendMessageToAllClient(String message){
		for(PrintWriter writer : allOut){
			writer.println(message);
		}
	}
	
	
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	/**
	 * 该线程体用于读取给定客户端的信息
	 * @author Administrator
	 *
	 */
	public class GetClientInfoHandler 
															implements Runnable{
		
		//当前线程要处理的客户端的Socket
		private Socket socket;
		public GetClientInfoHandler(Socket socket){
			this.socket = socket;
		}
		
		public void run(){
			PrintWriter writer = null;
			try {
				/**
				 * 先通过该客户端的socket获取输出流
				 * 用于给该客户端发送信息
				 * 这里我们将该输出流放入共享的集合中.
				 */
				OutputStream out = socket.getOutputStream();
				OutputStreamWriter osw
								= new OutputStreamWriter(out,"UTF-8");
				writer  = new PrintWriter(osw,true);
				
				//将当前客户端的输出流存入共享集合
				addOut(writer);
				System.out.println("当前在线人数:"+allOut.size());
				
				
				InputStream in = socket.getInputStream();
				InputStreamReader isr
											 = new InputStreamReader(in,"UTF-8");
				BufferedReader reader
											 = new BufferedReader(isr);
				
				String info = null;
				/**
				 * 若读取客户端发送过来的数据为null
				 * 说明连接出现了异常
				 */
				while((info = reader.readLine())!=null){
//					/**
//					 * 遍历保存所有客户端输出流的集合,将当前客户端
//					 * 发送的字符串转发给所有客户端
//					 */
					sendMessageToAllClient(info);
					/**
					 * 不直接转发了,存在多线程同时向一个输出流写
					 * 数据的问题.
					 * 我们将读取到的数据放入消息队列,等待转发
					 * 线程做同一的转发操作.
					 */
					messageQueue.offer(info);
					
					
//					System.out.println("客户端说:"+info);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				/**
				 * 当执行到这里,说明用户连接已经失效了
				 * 应当将该用户的输出流从共享集合中删除
				 * 并将该客户端的Socket关闭.
				 */
				//1 删除客户端的输出流
				removeOut(writer);
				
				String ip = socket.getInetAddress().getHostAddress();
				System.out.println(ip+"下线了.");
				
				//2 将socket关闭
				try {
					/**
					 * 当调用socket的close方法与客户端断开连接时
					 * 该方法会自动将通过socket获取的输入流与输出
					 * 流关闭.
					 * 我们通常不会显示的调用这两个流的关闭方法
					 */
					socket.close();
				} catch (IOException e) {
				}
				System.out.println("当前在线人数:"+allOut.size());
				
			}
		}
	}
	
	/**
	 * 从消息队列中获取信息,并转发给所有客户端的线程
	 * 该线程在服务端只有一个实例.
	 * @author Administrator
	 *
	 */
	public class SendMessageToAllClientHandler 
																implements Runnable{
			public void run(){
				String info = null;
				while(true){
					//从消息队列中获取一个信息
					info = messageQueue.poll();
					//取到了一条消息
					if(info != null){
						//转发给所有客户端
						sendMessageToAllClient(info);
					}else{
						//所有消息都转发完毕后,休息30毫秒
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}
	}
	
}






