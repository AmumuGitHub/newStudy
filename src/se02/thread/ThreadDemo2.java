package se02.thread;
/**
 * 创建线程的第二种方式
 * 
 * @author Administrator
 *
 */
public class ThreadDemo2 {
	
	public static void main(String[] args) {
		
		Runnable r1 = new PersonA();
		Runnable r2 = new PersonB();
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
	}
	
}

class PersonA implements Runnable{

	public void run() {
			for(int i=0;i<10000;i++){
				System.out.println("你是谁啊?");
			}
	}
}
class PersonB implements Runnable{

	public void run() {
			for(int i=0;i<10000;i++){
				System.out.println("我是修水管的");
			}
	}
}










