/**
 * 
 */
package basic.day04;

import java.util.Scanner;

import org.junit.Test;

/**
 *<p>Title: day04</p>
 *<p>description: </p>
 * @author amumu
 * @date 2016下午05:29:10
 */
public class day04 {
	//@Test
	public void testRuntimeException(){
		char c = 'A';
		char ch = 'A'+1;
		//ch = c + 1;//编译错误!
		ch = (char)(c + 1);
		//ch = 'A'+65530;//编译错误
		ch = 'A'*8;//没有编译错误
		ch = 30*1000;
		ch = 7;
		System.out.println(ch); 
	}
	
	//@Test
	public void testConcat(){
		System.out.println('1'+1+"2");//502
		System.out.println(1+1+"2");//22
		System.out.println('1'+1+'2');//100
		System.out.println("1"+1+"2");//112
		System.out.println("1"+1+'2'); //112
		System.out.println(10F/3);
	}
	
	//@Test
	public void testBoolean(){
		int age = 15;
		boolean isChild = age<16;// <= >= > != ==
		System.out.println(isChild);//true
		char sex = '女';
		boolean isMan = sex == '男';
		System.out.println(isMan); //false
		
		char c = '9';//66  
		// 'A':65  ~ 'Z':90
        //  65 =< c <= 90 就是大写英文字符.
		// Java的写法 c>=65 && c<=90 
		//运算过程: 
		// 66>=65 && 66<=90
		// true   && true 
		//       true
		if(c>='A' && c<='Z'){
			System.out.println(c+"是英文大写字母"); 
		}else{
			System.out.println(c+"不是英文大写字母"); 
		}
		
		//2) 判断一个字符是否是英文字符 
		c = 'b';
		
		if((c>='A'&&c<='Z') || (c>='a'&&c<='z')){
			System.out.println(c+"是英文字符");
		}else{
			System.out.println(c+"不是英文字符");
		}
	}
	
	//@Test
	public void testCuttingOut(){
		//测试短路逻辑: 第一个表达式能够确定结果, 就不执行第二个了
		int age = 25;
		char sex = '男';//会发生短路的条件
		if(sex=='女' && age++ >=60){
			System.out.println("欢迎光临!");
		}else{
			System.out.println("下次再来!");
		}
		System.out.println(age+"是25,是短路运算!"); 
//非短路逻辑运算: 两个表达式都执行!
		if(sex=='女' & age++ >=60){
			System.out.println("欢迎光临!");
		}else{
			System.out.println("下次再来!");
		}
		System.out.println(age+"是26,是非短路运算!"); 
// 短路的或运算
		age = 25;
		sex = '女';
		if(sex=='女' || age++ >= 60){
			System.out.println("欢迎光临!");
		}else{
			System.out.println("下次见");
		}
		System.out.println(age);//25, 说明发生了短路
//非短路或运算 
		if(sex=='女' | age++ >= 60){
			System.out.println("欢迎光临!");
		}else{
			System.out.println("下次见");
		}
		System.out.println(age);//26, 说明发生了非短路
	}
	
	//@Test
	public void testBooleanExpression(){
		Scanner console = new Scanner(System.in);
		System.out.print("输入查询结果行数:");
		int rows = console.nextInt();//读取整数
		//int rows = 33;//行数
		int size = 10;//每页大小
		int pages;
		pages = rows%size==0 ? rows/size : rows/size+1;
		System.out.println("页数:"+pages); 
	}
	//@Test
	public void testMod2(){
		int a,b,c;
		System.out.println(a=5);//输出"赋值表达式"的结果
		System.out.println(a);

		System.out.println(c = b = a = 8);//8
		//               c = (b = (a = 8))
		a = 1;
		a = a+2;// 
		a+=2;//与a = a+2 相当
		System.out.println(a);//5
		//"+="复合赋值运算
		a*=2;// a = a*2;
		System.out.println(a);//10
		
		int n = 38127;
		int l = n%10;
		System.out.println(l);//7
		n/=10;// n = n/10; 3812
		l = n%10;
		System.out.println(l);//2
		n/=10;//n=381
		l = n%10;
		System.out.println(l);//1
		n/=10;
		l=n%10;
		System.out.println(l);//8
		n/=10;
		l=n%10;
		System.out.println(l);//3
	}
	
	@Test
	public  void testFindMax(){
		int a,b,c,max;
		Scanner console = new Scanner(System.in);
		// 56 77 88 
		System.out.print("输入3个数(a b c):"); 
		a=console.nextInt();
		b=console.nextInt();
		c=console.nextInt();
		max = a>b? a:b>c?a:c;
		System.out.println(max+"是最大的!"); 
	}
}
