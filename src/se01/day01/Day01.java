/**
 * 
 */
package se01.day01;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

/**
 *<p>
 * Title: Test
 * </p>
 *<p>
 * description:
 * </p>
 * 
 * @author amumu
 * @date 2016下午09:13:45
 */
public class Day01 {
	//@Test
	public void testString1() {
		String s1 = "What";
		String s2 = s1;
		String s3 = "isJava";
		/**
		 * 这里对s1的内容进行了修改 那么就会产生一个新对象,里面保存"WhatisJava"
		 */
		s1 = s1 + s3;
		System.out.println(s1);// WhatisJava
		System.out.println(s2);
		System.out.println(s3);

		String str1 = "WhatisJava";
		String str2 = "WhatisJava";

		String str3 = new String("WhatisJava");

		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		/**
		 * String重写了Object提供的equals方法 比较原则是两个对象中的字符是否完全相同
		 */
		System.out.println("str1.equals(str3)?" + str1.equals(str3));
	}

	//@Test
	//jvm编译的优化问题
	public void testString2(){
		String s1 = "abc123";
		String s2 = "abc123";
		/**
		 * jvm编译时的优化,这里会先将两个字面量计算,将结果
		 * 直接放在等号右边
		 */
		String s3 = "abc"+"123";
		
		String s4 = "abc";
		String s5 = s4 + "123";
		/**
		 * 字符相加不等同合并字符串
		 */
		String s6 = 'a' + 'b' + 'c' + 1 + "23";
		
		System.out.println(s6);
		
		System.out.println(s1 == s2);//?
		System.out.println(s1 == s3);//?
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
	}
	
	//@Test
	//equals and equalsIgnoreCase
	public void testString3(){
		String str = "helloworld";
		if("helloworld".equals(str)){
			System.out.println("字符串完全相同");
		}else{
			System.out.println("字符串不相同");
		}
		
		if("HELLOWORLD".equalsIgnoreCase(str)){
			System.out.println("忽略大小写后字符串相同");
		}else{
			System.out.println("字符串不相同");
		}
		System.out.println("str.toLowerCase()=="+str.toLowerCase());
		System.out.println("str.toUpperCase()=="+str.toUpperCase());
		System.out.println("str.startsWith(hel)=="+str.startsWith("hel"));
		System.out.println("helloworld.trim== "+"helloworld ".trim().equals(str));
		System.out.println("helloworld.length=="+str.length());
		System.out.println("helloworld.length=="+str.length());
	}
	//@Test
	public void testString4() throws UnsupportedEncodingException{
		/**
		 * 这是一本好书.
		 */
		//            0123456789012345
		String str = "Thinking in Java";
		int index = str.indexOf("in");
		System.out.println(index);
		index = str.indexOf("in", 6);
		System.out.println(index);
		index = str.lastIndexOf("in");
		System.out.println("last:" + index);
		System.out.println(str.charAt(9));
		System.out.println(str.substring(12,str.length()));
		
		String info = "上海自来水来自海上";
		for(int i =0;i<info.length()/2;i++){
			
			if(info.charAt(i)!=info.charAt(info.length()-1-i)){
						System.out.println("不是回文");	
						return;
			}
			
		}
		System.out.println("是回文");
		
		str = "我爱java";
		/**
		 * 将当前字符串按照系统默认字符集转换为字节数组
		 */
		byte[] arry = str.getBytes("utf-8");
		System.out.println(Arrays.toString(arry));
		/**
		 * 字符集的名字不区分大小写
		 */
		byte[] array = str.getBytes("GBK");
		System.out.println(Arrays.toString(array));
	}
	
	@Test
	//String 字符串常量 StringBuffer 字符串变量（线程安全） StringBuilder 字符串变量（非线程安全）
	public void testStrBuf(){
		long begin;long end;
		begin=System.currentTimeMillis();
		String str = "a";
		for(int i = 0;i<100000;i++){
			str=str+"a";
		}
		end=System.currentTimeMillis();
		System.out.println("string拼接字符串完毕,花费"+(end-begin)/1000+"秒");
		begin=System.currentTimeMillis();
		StringBuilder builder  = new StringBuilder("a");
		for(int i =0;i<100000;i++){
			builder.append("a");
		}
		end=System.currentTimeMillis();
		System.out.println("StringBuilder拼接字符串完毕,花费"+(end-begin)/1000+"秒");
	}
}
