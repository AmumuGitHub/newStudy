/**
 * 
 */
package basic.day02;

import java.util.Scanner;

import org.junit.Test;

/**
 *<p>Title: day02</p>
 *<p>description: </p>
 * @author amumu
 * @date 2016上午10:54:17
 */
public class day02 {
	
	/**
	 * Scanner 
	 * 读取控制台输入内容测试;
	 */
	//@Test
	public void testScanner(){
		Scanner console = new Scanner(System.in);
		String name = console.nextLine();
		System.out.println("Hello "+name);
	}
	/**
	 * Java 变量的规则, Java 是强类型的语言! 
	 * 编译错误: 在.java文件编译为.class期间出现的错误
	 *   一般是语法层面的错误.(在Eclispe中出现的红色 叉叉)
	 * 运行异常: 是.class在运行期间,出现的问题.
	 *   (在控制台出现的红色异常提示, 是运行异常)
	 * 
	 * 变量的规则:
	 *  1) 变量必须声明以后才能使用!
	 *  2) 变量必须初始化(第一次赋值), 才能读取!
	 *  3) 变量可以改变值
	 *  4) 变量有作用域, 离开作用域时候, 会自动回收
	 *  5) 在变量的作用域中, 不能重复定义变量.
	 */
	//@Test
	public void testVariable(){
		//编译错误,没有声明age
		//System.out.println(age);
		//功能上表示: age表示整数类型的年龄
		int age;//声明变量, 执行时候在内存中就会
		//编译错误,age没有初始化
		//System.out.println(age);
		//选择结果: A.编译错误  B.运行异常  C.0 D.16
		age = 16;//初始化, 就是第一次赋值 
		System.out.println(age);//16
		age = 18;
		System.out.println(age);//18
		//开辟一个空间存储数据
		//age = 5.5;// 编译错误, 5.5不能给整数赋值
		//String s = null;
		//s.toCharArray();//运行异常, 以后讲
		
		{//代码块
			int score = 100;//分数
			System.out.println(score);//100
		}//代码块的结束
		//int age = 20;//编译错误, 不能重复定义变量age
		System.out.println(age);//18
		//System.out.println(aga);//编译错误,不存在
		//System.out.println(score);//编译错误,不存在
		int score = 90;
	}
	/**
	 * 正数： 原码=反码 =补码
	 *     [+1] = [00000001]原 = [00000001]反 = [00000001]补
	 * 负数：原码：[-1]原 = 10000001
	 *     反码：负数的反码是在其原码的基础上, 符号位不变，其余各个位取反  [11111110]反
	 *     补码：负数的补码是在其原码的基础上, 符号位不变, 其余各位取反, 最后+1. (即在反码的基础上+1)  [11111111]补
	 *     [-1] = [10000001]原 = [11111110]反 = [11111111]补
	 * 计算机的内存中是补码!
	 */
	@Test
	public void testMachineCode(){
		//利用补码公式: -n = ~n + 1 计算如下代码
		int n=6;
		System.out.println("n="+n+"; "+"~n="+~n+";");
		n = -6;// -n = ~n + 1 => -(-6) = ~(-6) + 1
		System.out.println("n="+n+"; "+"~n="+~n+";");
		int i= -6+3;
		//-6 在内存中是补码  3 也是补码 计算机是补码运算
		//运输结果是补码 -3, 在输出的时候转换为10进制的 "-3"
		System.out.println(i);//"-3" 
		// y = f(g(x))  
		System.out.println(Integer.toBinaryString(i)); 
		System.out.println(Integer.toBinaryString(~i+1)); 
		
		//16进制是2进制缩写
		i = 0xffffffff;//缩写了32个1
		System.out.println(i);//-1
		System.out.println(Integer.toBinaryString(i));
		
		i = 0xea;
		System.out.println(i); 
		System.out.println(Integer.toBinaryString(i)); 
	};
	
}
