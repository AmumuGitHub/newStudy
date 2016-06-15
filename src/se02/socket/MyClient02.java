package se02.socket;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import se02.socket.Client.GetServerInfoHandler;

/**
 * @author amumu
 * @date 2016下午08:34:48
 */
public class MyClient02 {
	private Socket socket;
	private String host;
	private int port;
    public MyClient02(String host,int port){
    	this.host=host;
    	this.port=port;
    	try {
			System.out.println("启动客户端,并尝试连接服务器");
			socket=new Socket(host,port);
			System.out.println("与服务器连接成功!");			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	public static void main(String[] args) {
		MyClient02 myClient=new MyClient02("127.0.0.1",8088);
		myClient.start();
	}

	/**
	 * 该方法中开始与服务端进行交互工作
	 */
	private void start() {
		try {
			//启动用于接收服务器发送过来的信息的线程
			Runnable handler = new GetServerInfoHandler();
			Thread t = new Thread(handler);
			t.setDaemon(true);//后台线程
			t.start();
			
			OutputStream os=socket.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
			PrintWriter pw=new PrintWriter(osw,true);
			Scanner scanner = new Scanner(System.in);
			while(true){
			   String str=scanner.nextLine();
			   pw.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 该线程用于获取服务器发送过来的信息
	 * @author Administrator
	 *
	 */
	public class GetServerInfoHandler implements Runnable{
		public void run(){
			try {
				InputStream in = socket.getInputStream();
				InputStreamReader isr= new InputStreamReader(in,"UTF-8");
				BufferedReader reader= new BufferedReader(isr);
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
