/**
 * 
 */
package se01.day02;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 *
 *<p> Title: Day02 </p>
 *<p>description:</p>
 * @author amumu
 * @date 2016下午11:13:54
 */
public class Day02 {

	// @Test
	public void testDate1() {
		/**
		 * 创建的d对象描述的是创建该对象那一刻的当前系统时间
		 */
		Date d = new Date();
		/**
		 * 获取自1970年元旦到这一刻的毫秒值
		 */
		System.out.println(d);
		long t = d.getTime();
		System.out.println(t);
		d.setTime(t);
		System.out.println(d);
		/**
		 * setTime(long time) 使当前Date对象描述给定的毫秒值所表示的时间
		 */
		t = t + 1000 * 60 * 60 * 24;
		d.setTime(t);

		// int y = d.getYear();//已经过时的方法,不要使用

		System.out.println(d);

	}

	// @Test
	public void testDate2() {
		// 当前系统时间
		Date now = new Date();

		System.out.println(now);// 打桩

		/**
		 * 工厂方法 工厂模式
		 */
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
				Locale.CHINA);
		String time = df.format(now);
		System.out.println(time);

	}

	//@Test
	public void testHeHe() {
		// 不用除算2个数的相除
		int A = 50;
		int B = 3;
		int Num = 0;
		int R = 0;
		while (true) {
			if (A - B >= 0) {
				A = A - B;
				Num++;
			} else {
				R = A;
				break;
			}
		}
		System.out.println("R==" + R);
		System.out.println("Num==" + Num);

	}
	
	//@Test
	public void testSdf() throws ParseException{
		/**
		 * y : 年     yyyy:四位的年   yy:两位的年
		 * M : 月     MM:两位的月  注意是大写
		 * d : 日     dd:两位的日
		 * h : 时    hh:2位12小时制的小时
		 * H : 时    HH:2位24小时制的小时
		 * m : 分    mm:2位的分
		 * s : 秒
		 * E : 星期
		 * a : 上下午 
		 */
		/**
		 *   2011年11月18日  16:25:30
		 */
		String str = "yyyy年MM月dd日 a hh:mm:ss E";
		Date now = new Date();
		System.out.println(now);
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		String timeStr = sdf.format(now);
		System.out.println("timeStr=="+timeStr);
		
		String strPattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf2 = new SimpleDateFormat(strPattern);
		String time = "1998-03-20 23:15:30";
		Date date = sdf2.parse(time);
		System.out.println("date=="+date);
		
	}
	
	@Test
	public void testStringBuild(){
		/**
		 * 创建一个内部是空字符串的StringBuilder
		 */
		String str = "好好学习java,为了找个好工作";
		StringBuilder builder = new StringBuilder(str);
		//好好学习java,为了更好的生活!
		builder.replace(11, builder.length(), "更好的生活!");		
		System.out.println(builder);
		//好好学习java,
		builder.delete(9, builder.length());
		
		//好好学习java,就是为了改变世界
		builder.append("就是为了改变世界");
		
		// ,就是为了改变世界
		builder.delete(0, 8);
		
		// 活着,就是为了改变世界
		builder.insert(0, "活着");
		System.out.println(builder);
		builder.reverse();
		System.out.println(builder);
		System.out.println(builder);
		System.out.println(str);
	}
	
	@Test
	public void testStringRegex(){
		/**
		 * 验证邮箱
		 * \w+@\w+(\.com|\.cn|\.com.cn)
		 *
		 */
		String regex = "\\w+@\\w+(\\.com|\\.cn|\\.com\\.cn|\\.gov)";
		//邮箱地址
		String mail = "fancq@tarena.com.cn";
		/**
		 * boolean matches(String regex)
		 * 使用给定的正则表达式匹配当前字符串格式
		 * 通过返回true
		 */
		boolean match = mail.matches(regex);
		
		if(match){
			System.out.println("是邮箱");
		}else{
			System.out.println("不是邮箱");
		}
		
		//split    replaceAll
	
	}
	@Test
	public void testStringUtils(){
		/**
		 * 将给定的字符串重复给定次后返回
		 */
		String repeat = StringUtils.repeat("hello",10);
		System.out.println(repeat);
		
		/**
		 * 与spilt是反向操作
		 */
		String[] array = {"a","b","c","d","e"};
		String join = StringUtils.join(array,",");
		System.out.println(join);
		/**
		 * 当给定的字符串不满足给定长度时,左面补若干个
		 * 给定字符,以达到指定长度.
		 */
		String leftPad = StringUtils.leftPad("687", 20,' ');
		System.out.println(leftPad);
		
		String rightPad = StringUtils.rightPad("765", 20,' ');
		System.out.println(rightPad);
	}

}
