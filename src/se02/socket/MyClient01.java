package se02.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author amumu
 * @date 2016下午08:34:48
 */
public class MyClient01 {
	private Socket socket;
	private String host;
	private int port;
    public MyClient01(String host,int port){
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
		MyClient01 myClient=new MyClient01("127.0.0.1",8088);
		myClient.start();
	}

	/**
	 * 该方法中开始与服务端进行交互工作
	 */
	private void start() {
		try {
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
}
