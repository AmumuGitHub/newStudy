package se02.thread;
/**
 * 进程什么时候退出?
 * 进程结束:
 * 当一个进程中的所有前台线程全部死亡后,进程结束
 * @author Administrator
 *
 */
public class ThreadDemo4 {
	public static void main(String[] args) {		
		Runnable r = new Runnable(){
			public void run(){
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
				}
			}
		};		
		Thread t = new Thread(r);
		t.start();		
		System.out.println("main方法执行完了");
	}
}
