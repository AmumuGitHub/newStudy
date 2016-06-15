package se02.thread;

/**
 * 前台线程与后台线程
 * @author Administrator
 *
 */
public class ThreadDemo5 {
	public static void main(String[] args) {
		 Thread rose = new Thread(){
			 public void run(){
				 for(int i=0;i<10;i++){
					 System.out.println("rose:let me go!");
					 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 }
				 System.out.println("rose:AAAAAAaaaaaa.....");
				 System.out.println("噗通.");
			 }
		 };
		 
		 
		 Thread jack = new Thread(){
			 public void run(){
				 while(true){
					 System.out.println("jack:you jump!i jump!");
					 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 }
			 }
		 };
		 
		 rose.start();
		 //设置为守护线程,要在线程启动前设置
		 jack.setDaemon(true);
		 jack.start();
	}
}






