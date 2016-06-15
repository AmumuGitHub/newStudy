package se02;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Day03 {

	// @Test
	public void testZhiShu() {
		System.out.println("getZhishu1Begin");
		getZhishu1();
		System.out.println("getZhishu1End:");
		// String s = null;
		// s = s+"word";
		// System.out.println("hello " +s);
	}

	public static void getZhishu1() {
		List<Long> timeList = new ArrayList<Long>();
		long start;
		long end;
		long costTime;
		boolean[] isZhishu = null;
		for (int times = 0; times < 10; times++) {
			start = System.currentTimeMillis();
			int n = 100;
			isZhishu = new boolean[n + 1];
			for (int i = 2; i <= n; i++) {
				for (int j = 2; j <= n / i; j++) {
					isZhishu[i * j] = true;
				}
			}
			end = System.currentTimeMillis();
			costTime = end - start;
			timeList.add(costTime);
			System.out.println("本次用时" + costTime);
		}

		// IntStream.range(2,
		// numAreo.length).forEach((i)->{System.out.println("整数"+i+(numAreo[i]?"不是质数":"是质数"));});
		for (int i = 2; i < isZhishu.length; i++) {
			System.out.println("整数" + i + (isZhishu[i] ? "不是质数" : "是质数"));
		}
	}

	// @Test
	public void testMap() {
		List list = new ArrayList();
		Map map1 = new HashMap();
		map1.put("id", 1);
		map1.put("orgId", 100);
		map1.put("year", 2015);
		map1.put("value", 20000);
		list.add(map1);

		Map map2 = new HashMap();
		map2.put("id", 1);
		map2.put("orgId", 100);
		map2.put("year", 2015);
		map2.put("value", 10000);

		// 只要map中 id,orgId,year 相同，list.contains(map2)就要为ture怎么解决？？？？？
		System.out.println(list.contains(map2));

		System.out.println(null == null);
	}

	
}
