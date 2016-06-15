package se02.thread;

/**
 * 线程优先级
 * 线程优先级越高的,理论上获得CPU时间片的次数就高.被执行
 * 的次数就多.
 * @author Administrator
 *
 */
public class ThreadDemo6 {
	public static void main(String[] args) {
			
			Thread max = new Thread(){
				public void run(){
					for(int i =0;i<100;i++){
						System.out.println("max");
					}
				}
			};
			
			Thread norm = new Thread(){
				public void run(){
					for(int i =0;i<100;i++){
						System.out.println("norm");
					}
				}
			};
			
			Thread min = new Thread(){
				public void run(){
					for(int i =0;i<100;i++){
						System.out.println("min");
					}
				}
			};
			/**
			 * 优先级的取值范围
			 * 1-10
			 * 1最低
			 * 10最高
			 * 5是默认值
			 */
			max.setPriority(Thread.MAX_PRIORITY);
			min.setPriority(Thread.MIN_PRIORITY);
			
			min.start();
			norm.start();
			max.start();
			
	}
}





