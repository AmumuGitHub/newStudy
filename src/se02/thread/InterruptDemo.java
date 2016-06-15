package se02.thread;

/**
 * 中断阻塞演示
 * @author Administrator
 *
 */
public class InterruptDemo {
	public static void main(String[] args) {
		/**
		 * 林永健
		 */
		final Thread lin = new Thread(new Runnable(){
			public void run(){
				System.out.println("林:刚美完容,睡觉吧!");
				try {
					Thread.sleep(10000000);
				} catch (InterruptedException e) {
					System.out.println("林:干嘛呢!干嘛呢!都破了相了!");
				}
			}
		});
		/**
		 * 黄
		 */
		Thread huang = new Thread(new Runnable(){
			public void run(){
				System.out.println("黄:开始砸墙!");
				for(int i =0;i<5;i++){
					System.out.println("黄:80!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("咣当!");
				System.out.println("黄:搞定!");
				/**
				 * void interruput()
				 * 中断当前线程
				 */
				/**
				 * 局部内部类中若想引用该方法的其他局部变量
				 * 那么该变量必须是final的
				 * 
				 * 以当前案例:
				 * main方法中有局部内部类Runnable(){}
				 * 而这个Runnable的run方法要引用lin这个对象
				 * 而lin这个对象又是main方法中定义的局部变量
				 * 那么lin就必须是final的
				 */
				lin.interrupt();				
			}
		});
		
		lin.start();
		huang.start();
		
	}
}
