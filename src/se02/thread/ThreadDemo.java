package se02.thread;
/**
 * Thread类,每一个实例表示一个可以并发执行的线程
 * @author Administrator
 *
 */
public class ThreadDemo {
	public static void main(String[] args) {
			Thread t1 = new Person1();
			Thread t2 = new Person2();
			
			t1.start();
			t2.start();
	}
}
/**
 * 第一种创建线程的方式
 * 继承线程,并重写run方法
 * 弊端:
 * 线程与线程要执行的任务代码耦合在了一起
 */
class Person1 extends Thread{
	public void run(){
		for(int i =0;i<10000;i++){
			System.out.println("你是谁啊?");
		}
	}
}


class Person2 extends Thread{
	public void run(){
		for(int i =0;i<10000;i++){
			System.out.println("我是查水表的");
		}	
	}	
}














