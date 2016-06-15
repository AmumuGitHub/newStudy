package se01.day03;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import se01.day03.entity.Point;

/**
 * @author amumu
 * @date 2016上午12:48:51
 */
public class Day03 {
	// @Test
	public void TestCalendar1() {
		/**
		 * 可根据当前系统所处的地区与时区获取适当的 Calendar实现类实例 创建出的实例默认表示当前系统时间
		 */
		Calendar calendar1 = Calendar.getInstance();
		System.out.println(calendar1);
		/**
		 * Date getTime() 使用一个Date对象描述当前Calendar所表示的时间 并返回
		 */
		Date date = calendar1.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));

		// -----------------------------------------------------------
		Calendar calendar2 = Calendar.getInstance();
		/**
		 * Calendar允许我们设置一个指定的时间 void set(int field,int value)
		 */

		calendar2.set(Calendar.YEAR, 2008);
		/**
		 * 月份中 0表示1月 以此类推 也有常量对应
		 */
		calendar2.set(Calendar.MONTH, 11);
		/**
		 * 设置天 的单位有以下几个 DAY_OF_MONTH DAY_OF_WEEK DAY_OF_YEAR
		 * 
		 * DATE DAY_OF_MONTH 效果一致
		 * 
		 * HOUR 12小时制 HOUR_OF_DAY 24小时制
		 * 
		 * MINUTE 分
		 * 
		 * SECOND 秒
		 */
		// 当超出了该单位最大值时,会向上进位
		calendar2.set(Calendar.DATE, 32);

		Date date2 = calendar2.getTime();
		System.out.println(date2);

		// ------------------------------------------------------------------
		Calendar calendar3 = Calendar.getInstance();
		/**
		 * 计算时间的方法 void add(int field,int value)
		 * 
		 */
		// 将当前时间加30天
		calendar3.add(Calendar.DAY_OF_YEAR, 30);

		// 将当前时间减去1年
		calendar3.add(Calendar.YEAR, -1);

		System.out.println(calendar3.getTime());

		/**
		 * int get(int field) 根据给定的时间单位获取对应的值
		 */
		// 获取当前Calendar所描述的年份
		int year = calendar3.get(Calendar.YEAR);
		// 注意,获取的月份也是从0开始的.
		int month = calendar3.get(Calendar.MONTH) + 1;
		int day = calendar3.get(Calendar.DATE);

		System.out.println(year + "-" + month + "-" + day);
	}

	// @Test
	public void testCalendar2() throws ParseException {
		/**
		 * 思路: 先将字符串转换为Date 再将Date转化为Calendar 比如当前是: 2011-11-19 将当前月加1 变为下个月
		 * 2011-12-19 再将日设置为1号 : 2011-12-01 再将日减去1天 : 2011-11-30
		 * 将Calendar转换为Date 在将Date转换为String 解决问题
		 */
		Scanner scanner = new Scanner(System.in);
		String inputDate = scanner.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(inputDate);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.add(Calendar.MONTH, 1);
		calendar1.set(Calendar.DATE, 1);
		calendar1.add(Calendar.DAY_OF_YEAR, -1);
		date = calendar1.getTime();
		String info = sdf.format(date);
		System.out.println("月底为:" + info);

		// ---------------------------------------

		Calendar calendar = Calendar.getInstance();
		/**
		 * int getActualMaximum(int field) 获取给定时间单位所允许的最大值
		 */
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, maxDay);
		System.out.println(calendar.getTime());
	}

	//@Test
	public void testArrayList1() {
		/**
		 * 集合中所有类型元素均可存入.
		 */
		Collection c = new ArrayList();
		/**
		 * add() 向集合中添加元素
		 */
		c.add("one");
		c.add("two");
		c.add("three");
		c.add("three");
		/**
		 * int size() 获取当前集合的元素数量
		 */
		int size = c.size();
		System.out.println("集合元素数量:" + size);

		/**
		 * ArrayList重写了toString() 格式: [元素1.toString() , 元素2.toString() , ...]
		 */
		System.out.println(c);

		/**
		 * 清空集合
		 */
		c.clear();
		System.out.println("集合的元素数量:" + c.size());
		/**
		 * boolean isEmpty() 判断集合中是否包含元素 不包含返回true
		 */
		System.out.println("集合不包含元素:" + c.isEmpty());
	}

	//@Test
	public void testArrayList2() {
		List list = new ArrayList();

		list.add(new Point(1, 2));
		list.add(new Point(1, 2));
		list.add(new Point(3, 4));
		list.add(new Point(5, 6));

		System.out.println(list);

		Point p = new Point(1, 2);
		/**
		 * contains检查给定元素是否在集合中包含是根据 equals()方法来判断的
		 */
		if (list.contains(p)) {
			System.out.println("包含");
		} else {
			System.out.println("不包含");
		}

		System.out.println(list.size());// 3
		/**
		 * 删除同样也是根据equals比较的 remove()方法只删除集合中第一个与给定元素equals 相同的元素.
		 */
		list.remove(p);
		System.out.println(list.size());
		System.out.println(list);

	}
	
	/**
	 * 用于描述精度非常高的小数
	 * 或进行小数运算
	 * @author Administrator
	 */
	//@Test
	public void  testBigDecimal1() {
		BigDecimal d1 = new BigDecimal("3.0");
		BigDecimal d2 = new BigDecimal("2.9");
		BigDecimal d3 = d1.subtract(d2);
		System.out.println(d3);
		/**
		 *  BigDecimal add(BigDecimal d)        加
		 *  BigDecimal multiply(BigDecimal d)   乘
		 *  BigDecimal divide(BigDecimal d)     除
		 */
		/**
		 * 这里存在风险!商若是循环小数会有问题
		 */
//		BigDecimal d4 = d1.divide(d2);
		/**
		 * 使用带有舍入模式的除法
		 */
		BigDecimal d4 = d1.divide(d2,8,BigDecimal.ROUND_HALF_UP);
		System.out.println(d4);
		
		BigInteger sum = BigInteger.valueOf(1);
		//实现200的阶乘
		for(int i = 2;i<=200;i++){
			sum = sum.multiply(BigInteger.valueOf(i));
		}
		System.out.println(sum);
	}
	
	//@Test
	public void testLiveDay() throws ParseException{
		/**
		 * 思路:
		 * 将生日的字符串转换为对应的Date对象,从而获取
		 * 毫秒值
		 * 在创建一个表示当前系统时间的Date,也获取毫秒值
		 * 用当前时间的毫秒减去生日的毫秒就是经过的毫秒值
		 * 将其转换为天即可
		 */
		String day = "1985-05-25";
		String formatStr = "yyyy-MM-dd";
		SimpleDateFormat sdf= new SimpleDateFormat(formatStr);
		Date birDate = sdf.parse(day);
		Date now = new Date();
		long time = now.getTime() - birDate.getTime();
		System.out.println("恭喜您已经活了:"+(time/1000/60/60/24)+"天");
	}

	@Test
	public void testBaoZhuang(){
		/**
		 * 使用包装类可以将基本类型数据以"对象"的形式创建
		 */
//		Integer i = new Integer(1);
//		Integer i1 = new Integer(1);
//		
//		System.out.println(i == i1);     //false
//		System.out.println(i.equals(i1));//true
			
		/**
		 * 创建包装类实例的方式
		 * 使用valueOf()方式创建对象,那么该值会被缓存,当
		 * 有重复值需要创建对象时,就会重用之前创建的对象.
		 */
		Integer i = Integer.valueOf(1);
		Integer i1 = Integer.valueOf(1);
		
		System.out.println(i == i1);     //true
		
		/**
		 * 取出包装类中描述的基本类型数据
		 */
		int num = i.intValue();
		
		Double d = Double.valueOf(1.1);
		double dNum = d.doubleValue();
	
		System.out.println("print(i)=="+dNum);
		
		//----自动拆装箱--------
		/**
		 * 自动装箱特性
		 * 当虚拟机发现一个基本类型需要变成其包装类时
		 * 会自动执行Integer.valueOf()转换
		 */
		Integer j = 1;
		
		/**
		 * 自动拆箱特性
		 * 当虚拟机发现一个包装类需要转换为其对应的基本类型时
		 * 会自动调用该包装类实例的xxxValue()方法转换
		 * 例如:
		 * 	int j1 = j; 就会自动调用j.intValue()
		 */
		int j1 = j;
		
		String num2 = "123";
		/**
		 * 将字符串转换为对应的基本类型时要注意
		 * 确保字符串所描述的内容是我们要转换的基本类型数据
		 * 否则转换异常
		 */
		int i3 = Integer.parseInt(num2);
		i3++;
		System.out.println(i3);
		
		long l = Long.parseLong(num2);
		
		num2 = "123.123";
		double d2 = Double.parseDouble(num2);
		System.out.println(num2);
		
	}
	
	//@Test
	public void testBigInteger(){
		//int最大值
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		long maxL = Long.MAX_VALUE;
		//将数字转换为2进制
		String bStr = Integer.toBinaryString(100);
		
		//将数字转换为16进制
		String hStr = Integer.toHexString(100);
		
		System.out.println("2进制:"+bStr);
		System.out.println("16进制:"+hStr);
	}
	
}
