package se02.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * @author Administrator
 *
 */
public class Client {
	//客户端的Socket
	private Socket socket;
	
	public Client(){
		try {
			/**
			 * Socket(String hostName,int port)
			 * hostName:服务器的地址
			 * port:服务器打开的服务端口号
			 */
			System.out.println("启动客户端,并尝试连接服务器");
			socket = new Socket("localhost",8088);
			
			System.out.println("与服务器连接成功!");			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开始方法
	 * 该方法中开始与服务端进行交互工作
	 */
	public void start(){
		try {
			//启动用于接收服务器发送过来的信息的线程
			Runnable handler = new GetServerInfoHandler();
			Thread t = new Thread(handler);
			t.setDaemon(true);//后台线程
			t.start();
			
			/**
			 * OutputStream getOutputStream()
			 */
			OutputStream out = socket.getOutputStream();
			/**
			 * 将字节流转换为字符流
			 */
			OutputStreamWriter osw 
								= new OutputStreamWriter(out,"UTF-8");
			/**
			 * 转换为缓冲字符输出流
			 * 
			 * 创建带有自动行刷新的PrintWriter
			 * 每当我们调用println()方法时,会自动flush()
			 * 
			 * PrintWriter(OutputStream out,boolean autoFlush)
			 * 
			 * PrintWriter(Writer writer,boolean autoFlush)
			 */
			PrintWriter writer 
								= new PrintWriter(osw,true);
			
			/**
			 * 创建Scanner,用于读取键盘输入的内容
			 */
			Scanner scanner = new Scanner(System.in);
			
			while(true){
				/**
				 * 向服务器发送一个字符串
				 */
				writer.println(scanner.nextLine());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		//实例化客户端.
		Client client = new Client();
		//开始交互
		client.start();
	}
	
	/**
	 * 该线程用于获取服务器发送过来的信息
	 * @author Administrator
	 *
	 */
	public class GetServerInfoHandler
															implements Runnable{
		public void run(){
			try {
				InputStream in = socket.getInputStream();
				InputStreamReader isr
									= new InputStreamReader(in,"UTF-8");
				BufferedReader reader
									= new BufferedReader(isr);
				
				/**
				 * 循环读取服务器发送过来的信息,并输出到
				 * 控制台
				 */
				while(true){
					System.out.println(reader.readLine());
				}
				
			} catch (Exception e) {

			}
		}
	}
	
}






