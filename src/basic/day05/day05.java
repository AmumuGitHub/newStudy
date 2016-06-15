/**
 * 
 */
package basic.day05;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

/**
 *<p>
 * Title: day05
 * </p>
 *<p>
 * description:
 * </p>
 * 
 * @author amumu
 * @date 2016下午10:11:40
 */
public class day05 {
	// @Test
	public void testBigInteger() {
		// int i = 5;//"5"
		long sum = 1;
		// BigInteger s = new BigInteger("1");
		for (int i = 1; i <= 20; i++) {
			// i=1 2 3 4 ... 20
			sum *= i;// sum = sum * i
			// BigIngteger n = new BigInteger(""+i);
			// s = s.multiply(n)
		}
		System.out.println(sum);
		BigInteger n1 = new BigInteger("10");
		BigInteger n2 = new BigInteger("129837327272727272727000");
		BigInteger n3 = n1.multiply(n2);// 乘
		BigInteger n4 = n1.divide(n2);// 除
		BigInteger n5 = n1.add(n2); // 加
		BigInteger n6 = n1.subtract(n2);// 减
		System.out.println(n3);

		BigInteger s = new BigInteger("1");
		for (int i = 1; i <= 30; i++) {
			BigInteger n = new BigInteger("" + i);
			s = s.multiply(n);
		}
		System.out.println(s);
	}

	// @Test
	public void testPI() {
		double sum = 0;
		for (int i = 1; i < 100000; i += 4) {
			// i = 1 5 9 13 .... <10000
			sum += 1D / i - 1.0 / (i + 2);
		}
		double pi = sum * 4;
		System.out.println(pi);
	}

	// @Test
	// 数组如果是对象 默认值为null
	// 如果是基本数据类型， 默认值就是基本数据类型的初始值
	public void testArray() {
		float[] intArr = new float[5];
		System.out.println(intArr[0]);
	}

	// @Test
	// 判断一个数是否为
	public void testIsPrime() {
		int n = 40; // n = 2 3 4 5 ... 1000
		boolean isPrime = true;
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				isPrime = false;
				break;
			}
		}
		if (isPrime) {
			System.out.println(n + "is prime");
		} else {
			System.out.println(n + "is not prime");
		}
	}

	// @Test
	// 数字反转
	public void testNumberInverse() {
		int sum = 0;
		int n = 4232299;
		int last;
		while (n != 0) {
			last = n % 10;
			sum = sum * 10 + last;
			n = n / 10;
		}
		System.out.println(sum);
	}

	// @Test
	/**
	 * 水仙花数: 3位自幂数, 是一个3位数, 这个数的数值等于每个 数位上的数字3次幂的和 如: 153 = 1*1*1 + 5*5*5 +
	 * 3*3*3
	 */
	public void tesNarcissusNumber() {
		int num = 153;
		int n = num;
		int sum = 0;
		// while(n!=0){
		for (; n != 0;) {
			int last = n % 10;
			sum += last * last * last;
			n /= 10;
			// System.out.println(n+","+last+","+sum);
		}
		if (num == sum) {// 原数据num 与 sum 是否一致
			System.out.println(num + "是水仙花数");
		}
	}

	// @Test
	public void testDoWhile() {
		Scanner console = new Scanner(System.in);
		int score;
		do {
			System.out.print("输入有效的分数:");
			score = console.nextInt();// (1) score=85
		} while (score < 0 || score > 100);// (2)
		System.out.println(score);// (3)
	}

	// @Test
	public void testGuessNum() {
		int answer, guess, score = 0;
		Random random = new Random();
		Scanner console = new Scanner(System.in);
		answer = random.nextInt(100) + 1;
		System.out.println("answer==" + answer);
		while (true) {// for(;;){
			System.out.print("输入猜测数:");
			guess = console.nextInt();
			score++;
			if (guess == -1) {
				break;
			}
			if (answer == guess) {
				System.out.println("(-_-)次数" + score);
				break;
			} else if (guess > answer) {
				System.out.println(score + "次,猜大了!");
			} else {
				System.out.println(score + "次,猜小了!");
			}
		}
	}

	@Test
	public void testStrArray() {
		String[] students = { "关羽", "王菲", "李亚鹏" };
		// 0 1 2
		System.out.println(students[0]);// 关羽
		students = new String[] { "孙俪", "邓超", "文章" };
		System.out.println(students[0]);// 孙俪
	}

	@Test
	public void testAry() {
		int[] ary = new int[4];// 声明"数组变量"
		System.out.println(ary[0]);//编译错误,没有初始化d
		System.out.println(ary);// 变量值是null
		// System.out.println(ary[0]);//运行异常
		// 在值为null的变量上访问属性/方法/元素,会发生
		// 空指针异常
		// 选择执行结果:
		// A.编译错误 B.运行异常 C.null D.0
		// 如何创建数组 : 3种方式
		// 方式1
		ary = new int[3];// {0,0,0} 使用长度 创建数组
		// 数组元素:自动初始化为"零值"
		System.out.println(ary[1]);// 0
		boolean[] used = new boolean[3];
		// used = {false, false, false}
		System.out.println(used[1]);// false
		// 方式2 直接初始化元素
		ary = new int[] { 4, 5, 6 };// []中不能写长度
		System.out.println(ary[1]);// 5
		// 方式3 静态初始化, 相当于2的简化版
		// 静态初始化 只能用于声明变量时候直接初始化!
		int[] ary1 = { 5, 6, 7 };
		// ary = {5,6,7};//编译错误!
		// 访问数组元素
		// 1) 可以使用.length属性获得数组的长度
		// 2) 数组创建以后数组长度是不可改变的
		// 3) 数组下标(序号) 范围: 0 1 ... length-1
		// 4) 最后元素的位置: length-1
		// 5) 超范围访问数组元素, 有数组越界运行异常
		// 6) 使用[下标]读写数组元素
		ary = new int[] { 6, 7, 8 };
		System.out.println(ary.length);// 3
		// ary.length = 9;//编译错误
		System.out.println(ary[0]);
		System.out.println(ary[1]);
		System.out.println(ary[2]);
		// System.out.println(ary[3]);//运行异常!
		System.out.println(ary[ary.length - 1]);
		ary[2]++;// 写数组元素
		// 数组的"迭代" 迭代:遍历, 就是重复处理每个元素
		for (int i = 0; i < ary.length; i++) {
			// i=0 1 2
			System.out.println(ary[i]);
		}
	}

}
