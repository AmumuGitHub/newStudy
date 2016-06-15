/**
 * 
 */
package basic.day06;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

/**
 *<p>Title: day06</p>
 *<p>description: </p>
 * @author amumu
 * @date 2016上午08:14:58
 */
public class day06 {
	 
   //@Test
   public void testCard(){
		String[] cards = {"红桃3","方块4","梅花5",
				"黑桃6","方块A","黑桃2"};
		Random random = new Random();
		for(int i=cards.length-1; i>=1; i--){
			int j = random.nextInt(i);
			String t = cards[i];
			cards[i] = cards[j];
			cards[j] = t;
		}
		for(int i=0; i<cards.length; i++){
			System.out.print(cards[i]+" ");
		}
   }
   //@Test
   public void testHitPlane(){
	   boolean isHit = hit(100,200,80,152,245);
		System.out.println(isHit);//true
		isHit = hit(100, 200, 80, 190, 198);
		System.out.println(isHit);//false
		System.out.println(Math.sqrt(4729)); 
	   
  }
   
   public static boolean hit(int x, int y, int r, 
			int x1, int y1){
		int a = y1-y; int b = x1-x;
		double c = Math.sqrt(a*a+b*b);
		return c<=r;
	}
   
   /**
    * 计算 菲波纳切 数列
    *  1  1  2  3  5  8  13  21  .... f(40) f(41)
    *  1  2  3  4  5  6   7  8
    */
 // @Test
   public void testFibonacciSequence(){
		System.out.println(f(5)); 
		System.out.println(f(5)); 
		System.out.println(f(40)/f(41)); 
		System.out.println((double)f(40)/(double)f(41)); 
   }
   
   public static long f(long n){
		int f1=1;int f2=1;int fn=1;
		for(int i=3; i<=n; i++){
			fn=f1+f2;
			f1=f2;
			f2=fn;
		}
		return fn;
	}
   
    // @Test
    // 字母猜测游戏
    public void guessArray(){
    	char[] answer; char[] input;
		int[] result; int count=0;
		answer = generate(5);//生成5个字符
		System.out.println(answer); 
		for(;;){
			input = userInput();//获取用户的输入
			result = check(answer, input);//检查结果
			count++;//统计猜测次数
			if(result[0]==5 && result[1]==5){
				System.out.println("猜中!次数:"+count);
				break;//猜中结束
			}else{
				show(count, result);//显示猜测结果
			}
		}
    }
    /**
	 * 生成n个不重复的字符序列(经典的去除重复算法)
	 * @param n 字符个数
	 * @return n个不重复的字符序列
	 */
	public static char[] generate(int n){
		char[] chs = {'A','B','C','D','E','F','G',
				'H','I','J','K','K','M'};
		boolean[] used = new boolean[chs.length];
		int i; int index=0;
		char[] answer = new char[n];
		Random random = new Random();
		do{
			i = random.nextInt(chs.length);
			if(used[i]){continue;}
			answer[index++]=chs[i];
			used[i]=true;
		}while(index!=n);
		return answer;
	}
	
	/**
	 * 获得用户输入的5个字符
	 * @return
	 */
	public static char[] userInput(){
		Scanner in = new Scanner(System.in);
		String str;
		do{
			System.out.print("输入猜测字符:");
			str = in.nextLine();//从控制台读取一行字符串
		}while(str.length()!=5);
		//toCharArray()将字符串转换为字符数组
		// "ABEDF" -> [A B E D F]
		char[] chs = str.toCharArray();
		return chs;
	}
	
	public static int[] check(
			char[] answer, char[] input){
		int[] result = {0,0};
		///    {匹配字符个数, 匹配位置个数}
		for(int i=0; i<answer.length; i++){
			for(int j=0; j<input.length; j++){
				if(answer[i]==input[j]){
					result[0]++;
					if(i==j){
						result[1]++;
					}
					break;
				}
			}
		}
		return result;
	}
	public static void show(int count, int[] result){
		System.out.println("猜测了"+count+"次,"+
				"匹配字符:"+result[0]+ 
				"匹配位置:"+result[1]);
	}
   
	//@Test
	//测试一些java方法
	public void testArrMethods(){
		String[] names = {"Jerry","Tom","Andy"};
		System.out.println(names);//很丑陋的结果
		//Java中最便捷输出数组全部内容的办法
		System.out.println(Arrays.toString(names));
		
		Arrays.sort(names);
		System.out.println(Arrays.toString(names));
		
		char[] answer = {'A','C','D'};
		char[] input = {'A','C','D'};
		boolean pass = Arrays.equals(answer, input);
		System.out.println(pass);//true
		
		//二分查找，要先sort,才能保证正确
		String[] hotel = {"Tom", "Andy","Jerry", "Lee", "Mac"};
		// 0       1      2       3      4
		//查找 Andy, 返回结果: -1 没找到
		System.out.println("查找 Andy:"+Arrays.binarySearch(hotel, "Andy"));//-1
		//查找 Jerry, 返回结果: 2 房间号
		System.out.println("查找 Jerry:"+Arrays.binarySearch(hotel, "Jerry"));//2
		Arrays.sort(hotel);
		System.out.println("hotel"+Arrays.toString(hotel));
		//{ "Andy","Jerry","Lee","Mac","Tom"};
		//     0      1      2     3     4
		//查找 Andy, 返回结果: 0 房间号
		System.out.println("查找 Andy:"+Arrays.binarySearch(hotel, "Andy"));//0
		//查找 Tom, 返回结果: 4
		System.out.println("查找 Tom:"+Arrays.binarySearch(hotel, "Tom"));//4
		//查找 Nemo, 返回结果: 负数
		System.out.println("查找 Nemo:"+Arrays.binarySearch(hotel, "Nemo"));
		
		
		int[] ary1 = {4,5,6,7};
		//            0 1 2 3 
		int[] ary2 = {5,6,7,8};
		//数组复制: 将ary1的内容复制到ary2
		//从ary1数组中0位置开始, 复制到ary2中0位置,
		//连续复制 3 个
		System.arraycopy(ary1, 0, ary2, 0, 3);
		System.out.println(Arrays.toString(ary1));
		System.out.println(Arrays.toString(ary2));
		
		//jdk6----
//		//Arrays.copyOf() 复制成新数组
//		int[] ary3 = Arrays.copyOf(ary1, 5);
//		System.out.println(Arrays.toString(ary3)); 
//		//数组的扩容(数组变长算法)
//		int[] ary4 = {1,2,3,4};
//		System.out.println(Arrays.toString(ary4));
//		ary4 = Arrays.copyOf(ary4, ary4.length+1);
//		System.out.println(Arrays.toString(ary4)); 
//		//追加元素
//		ary4[ary4.length-1]=7;
//		System.out.println(Arrays.toString(ary4)); 
		
	}
	
	@Test
	public void testF(){
		Integer a=Integer.valueOf("1");
		Integer b=Integer.valueOf("1");
		System.out.println(a==b);

	}
}
