/**
 * 
 */
package basic.day03;

import java.util.Random;

import org.junit.Test;

/**
 *<p>
 * Title: day03
 * </p>
 *<p>
 * description:
 * </p>
 * 
 * @author amumu
 * @date 2016下午12:36:34
 */
public class day03 {
	//@Test
	public void testBinary() {
		int i = -1;
		System.out.println(i); // -1
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(-2));	
	}
	//@Test
	public void testNumber() {
		int i = 5;//使用int类型字面量5给int变量i初始化
		System.out.println(i);//5
		//long l=1000000000000;//编译错,字面量超过int范围
		long l = 100000000000L;//使用long类型的字面量赋值
		System.out.println(l); 
	}
	
	//@Test
	public void testYear() {
		long now = System.currentTimeMillis();
		System.out.println(now); 
		long year = now/1000/60/60/24/365 + 1970; 
		System.out.println(year);
		long max = 0x7fffffffffffffffL;
		System.out.println("long类型计时结束的公元年份");
		year = max/1000/60/60/24/365 + 1970;
		System.out.println(year); 
	}
	
	//@Test
	public void testMath() {
		//float f = 3.1415926535897932;//编译错,类型错误
		float f = 3.1415926535897932f;
		double pi = 3.1415926535897932384626;
		System.out.println("double 比 float精确!");
		System.out.println("f=="+f+";  pi=="+pi);
		//Math 是Java 数学函数库(数学函数API)
		double y = Math.sin(pi);
		System.out.println("y=="+y); //y是不精确的0
		//abs() 绝对值
		if(Math.abs(y)<0.0000000000001){
			System.out.println("y接近于0"); 
		}
		double d = 2.6;
		double x = d-2;
		System.out.println(x);//不精确的结果,请参考IEEE754 
	}
	
	//@Test
	public void testChar() {
		char c= 30001;
		System.out.println(c); //'田'
		//对于Java来说 30000 和 '田' 没有本质的差别
		int i = '中';//'刘'
		System.out.println(i);//20013
		System.out.println((int)'刘');
		System.out.println((int)'A'); 
		//输出每位同学姓名每个字的uincode编码
		//输出'A'~'Z', 'a'~'z' 的uincode编码
		//输出'0'~'9'的uincode编码
		for(int j=0;j<26;j++){
			System.out.print('A'+j+"、 ");
		}
		System.out.println();
		for(int j=0;j<26;j++){
			System.out.print('a'+j+"、 ");
		}
		
		System.out.println();
		for(int j=0;j<9;j++){
			System.out.print('0'+j+"、 ");
		}
	}
	
	//@Test
	public void testRandom() {
		//1 随机字符就是随机数字! 就是: [65,90]
		//2 生产随机数字 [65,90] 就可以了
		//3 random.nextInt(26)方法可以产生[0, 26)的随机数                          [0, 25]
		//4 [0, 26) + 65 = [65,91) = [65, 90] = ['A','Z']
		Random random = new Random();
		int n = random.nextInt(26);//n = [0, 26)
		int num = n+'A';//num = [65, 91)
		char c = (char)num;//c = ['A', 'Z']
		System.out.println(n+","+num+","+c);
		
		c = 65; // 01000001
		n = 65; // 01000001
		System.out.println("char c=="+c+";  int n=="+n);
		//n的类型是int, 调用算法输出65对应的10进制形式"65"
		//按照人的习惯输出! 以人为本!
		//重载现象!
		//println 打印
		//println 打印 int
		//println 打印 char 
		// 打 车
		// 打 牌
	}
	
	//@Test
	public void testCast() {
		//自动类型转换
		int i = 5;
		long l = i;//发生自动类型转换, int自动转换为long
		//Java将32位的int数据,补充了32位0 赋值给long类型l
		System.out.println(l);//5
		i = -1;//i 是 32个 1
		l = i;//Java为i补充32个1 赋值给l
		System.out.println(l);//long 类型 -1
		System.out.println("int填充32个1转换为long") ;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Long.toBinaryString(l));
		System.out.println("如下发生了自动(隐式)类型转换!");
		System.out.println(Long.toBinaryString(i));
//强制类型转换
		l = 6L;// 0 .32个0. 0  0.24个0.0 00000110   
		//i = l;//编译错误, 类型错误, long不能直接赋值给int
		i = (int)l;//强制类型转换--强转
		// i = 0.24个0.0 00000110 
		//如何"强转", Java会把l类型的高32位直接削去保留低32位
		System.out.println(i);//6
		//当没有超范围时候, 不会发生溢出问题
		l = 0xf720000000aL;
		i = (int)l;//削去 f72 
		System.out.println("超int范围, 溢出!");
		System.out.println(l);
		System.out.println(Long.toBinaryString(l)); 
		System.out.println(i);//10
		System.out.println("精度损失:");
		double pi = 3.1415926535897932384;
		float f = (float)pi;
		l = (long)pi;
		System.out.println(pi);
		System.out.println(f);
		System.out.println(l);
		
	}
	
	//@Test
	public void testOverflow() {
		int a = 5;
		int b = 6;
		int c = a+b;//a+b 称为表达式
		long l = 7;
		long y = l + a;//l + (long)a
		a = 0x7fffffff;//max
		//计算结果超int范围, 发生溢出
		c = a+1;//min
		System.out.println("max int a=="+a+";  max int a+1=="+c);
		l = a+1;//上溢出, int+int结果已经溢出了
		System.out.println(l); 
		l = a+1L;//不会溢出, (long)int+long 不会溢出 
		System.out.println(l); 
		
		a = 5;
		b = 2;
		c = a/b;//5 除以 2 得 2 余 1 //下溢出
		System.out.println(c);//2
		double d = a/b;//为啥不能解决问题
		System.out.println(d);//2.0
		d = (double)a/b;
		System.out.println(d);//2.5
		
		double price = 99.99;
		System.out.println(price*(80/100));//0
		System.out.println(price*(80.0/100));//
		System.out.println(price*(80D/100));//

		byte b1 = 1;
		byte b2 = 2;
		byte b3 = (byte) (b1+b2);//编译错误! 不能int赋值byte
		//byte b3 = (byte)b1+b2;//编译错误!
		//byte b3 = (byte)(b1+b2);
		b3 = 1+2;//没有编译错误!
		//b3 = 126+2;//编译错误, 126+2 超过byte范围!
		
	}
	
	//@Test
	public void testMod() {
		int a=5;
		int b=2;
		int c = a%b;//5除以2 得 2 余 1
		System.out.println(c);//1
		
		System.out.println(-4%3);//-4除以3 得 -1 余 -1
		System.out.println(-3%3);//-3除以3 得 -1 余 0
		System.out.println(-2%3);//-2除以3 得 0 余 -2
		System.out.println(-1%3);//-1除以3 得 0 余 -1
		System.out.println(0%3);//0除以3 得 0 余 0
		System.out.println(1%3);//1除以3 得 0 余 1
		System.out.println(2%3);//2除以3 得 0 余 2
		System.out.println(3%3);//3除以3 得 1 余 0
		System.out.println(4%3);//4除以3 得 1 余 1
		System.out.println(5%3);//5除以3 得 1 余 2
		System.out.println(6%3);//6除以3 得 2 余 0
		
	}
	
	@Test
	public void testIncrement () {
		int a = 1;
		a++; 
		System.out.println(a);//2
		++a;
		System.out.println(a);//3
		
		//表达式的值, 有区别
		a = 1;
		//输出a++整体表达式的值
		System.out.println((a++));//1
		//a++ 称为 后++: 先取值后加, 具体运算:
		//1)++运算 先取a的值1 作为a++表达式的值1 
		//2)++运算 然后将a的值1 增加1 a为2
		//3)println() 执行println()输出a++表达式的结果1
		//输出a的值
		System.out.println(a); //2
		
		a = 1;
		a = a++;//先++ 然后 = 
		//1)++运算 先取a的值1 作为a++表达式的值1 
		//2)++运算 然后将a的值1 增加1 a为2
		//3)= 执行= a++表达式的结果1 赋值给a, a由2变为1
		System.out.println(a);//1
		
		int i = 0;
		
		System.out.println(i++%3);//0
		//1)++运算 先取i的值0 作为i++表达式的值0 
		//2)++运算 然后将i的值0 增加1 i为1
		//3)执行% i++表达式的结果0与3取余, 结果0
		System.out.println(i++%3);//1
		//1)++运算 先取i的值1 作为i++表达式的值1 
		//2)++运算 然后将i的值1 增加1 i为2
		//3)执行% i++表达式的结果1与3取余, 结果1
		System.out.println(i++%3);//2
		System.out.println(i++%3);//0
		System.out.println(i++%3);//1
		System.out.println(i++%3);//2
		System.out.println(i++%3);//0
		
		a=1;
		System.out.println(++a);//2
		//先++, 先a增加, 然后取a的值最为表达式++a的值
		System.out.println(a);//2
	}
}
