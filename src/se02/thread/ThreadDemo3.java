package se02.thread;
/**
 * 匿名类方式创建线程
 * @author Administrator
 *
 */
public class ThreadDemo3 {
	public static void main(String[] args) {
		/**
		 * 方式1的匿名类方式创建
		 */
		Thread t1 = new Thread(){
			public void run(){
				for(int i =0;i<10000;i++){
					System.out.println("你是谁啊?");
				}
			}
		};
		
		/**
		 * 方式2的匿名类方式创建
		 */
		Runnable r = new Runnable(){
			public void run(){
				for(int i =0;i<10000;i++){
					System.out.println("我是修水管的");
				}
			}
		};	
		
		Thread t2 = new Thread(r);
		t1.start();t2.start();
	}
}
