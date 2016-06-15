package se02.thread;
/**
 * 线程协同工作
 * @author Administrator
 */
public class ThreadDemo7 {
	static boolean finish=false;
	public static void main(String[] args) {
		final Thread download=new Thread(){
			public void run(){
				System.out.println("开始下载图片..");
				for(int i=0;i<100;i++){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("下载:"+i);
				}
				finish = true;//图片下载完毕
				/**通知在download身上等待的一个线程可以解除
				 * wait阻塞,继续工作了
				 */
				synchronized(this){
					this.notify();
				}
			}
		};
		
		Thread showImg=new Thread(){
			public void run(){
				synchronized (download) {
					try {
						download.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("准备显示图片..");
				if(!finish){
					throw new RuntimeException("图片还没有准备就绪!");
				}
				System.out.println("已经成功显示图片");
			}
		};
		showImg.start();
		download.start();
	}
}








