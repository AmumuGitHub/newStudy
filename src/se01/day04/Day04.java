package se01.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import se01.day04.entity.Point;

/**
 * @author amumu
 * @date 2016上午11:22:48
 */
public class Day04 {
	// @Test
	public void testCollectionToArray() {
		Collection c = new ArrayList();
		c.add("one");
		c.add("two");
		c.add("three");

		/**
		 * 将集合转换为数组
		 */
		Object[] objArray = c.toArray();
		System.out.println(Arrays.toString(objArray));

		/**
		 * 重载的toArray() 参数传入的数组类型即是返回的数组类型. 传入的数组不需要给定长度.
		 * 返回时依然以Object[]数组返回的,不过我们可以 强制类型转换为目标类型数组
		 */
		String[] strArray = (String[]) c.toArray(new String[0]);

		System.out.println(Arrays.toString(strArray));
	}

	// @Test
	public void testIterateCollection() {
		Collection c = new ArrayList();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("#");
		c.add("three");
		c.add("#");
		c.add("four");
		/**
		 * 遍历集合的三个步骤 问取删(删除不是必须的)
		 */
		Iterator it = c.iterator();
		// 问
		while (it.hasNext()) {
			// 取
			String str = (String) it.next();
			/**
			 * 将集合中的所有"#"删除
			 */
			if ("#".equals(str)) {
				/**
				 * 要求:在使用迭代器遍历集合时,不允许通过集合 的方法对结合元素删除
				 */
				// c.remove(str);
				/**
				 * 使用迭代器的remove()方法,可以将刚迭代出的元素 从集合中删除.
				 * 调用next()取出元素后,只能调用一次remove删除 元素
				 */
				it.remove();
			}
		}
		System.out.println(c);

	}

	// @Test
	public void testList1() {
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();

		list1.add("one");
		list1.add("two");
		list1.add("three");

		list2.add("four");
		list2.add("five");

		list3.add("one");
		list3.add("two");

		/**
		 * 将list2中的所有元素添加到list1中
		 */
		list1.addAll(list2);
		System.out.println(list1);// [one,two,three,four,five]

		/**
		 * 删除list1中与list3相同的元素
		 */
		list1.removeAll(list3);
		System.out.println(list1);// [three,four,five]

		/**
		 * 只保留list1中与list2相同的元素,其余删除
		 */
		list1.retainAll(list2);
		System.out.println(list1);// [four,five]

	}

	// @Test
	public void testList2() {
		List list = new LinkedList();
		list.add("one");
		list.add("two");
		list.add("three");

		System.out.println(list);

		/**
		 * Object get(int index) 根据下标获取对应的集合元素
		 */
		for (int i = 0; i < list.size(); i++) {
			String str = (String) list.get(i);
			System.out.println(str);
		}

		/**
		 * Object set(int index,Object o) 将集合中指定位置的元素替换为给定元素 返回被替换的元素
		 */
		Object old = list.set(2, "三");
		System.out.println(list);
		System.out.println(old);// three

		/**
		 * List集合中重载的 add(int index,Object o) 向集合中插入元素
		 */
		// [one,二,two,三]
		list.add(1, "二");
		System.out.println(list);

		/**
		 * List集合中重载的 Object remove(int index) 从集合中删除指定位置的元素,并返回该元素
		 */
		Object remove = list.remove(2);
		System.out.println(list);
		System.out.println("被删除的是:" + remove);

		/**
		 * int indexOf(Object o) 返回给定元素第一次在集合中出现的位置,若集合中 不包含该元素则返回-1
		 * 判断包含的行为依然为equals
		 */
		int index = list.indexOf("二");
		System.out.println("二:" + index);

		/**
		 * int lastIndexOf(Object o) 返回给定元素最后一次在集合中出现的位置
		 */
	}

	// @Test
	public void testList3() {
		/**
		 * 集合中的泛型约束的是集合中的元素类型
		 */
		List<String> list = new ArrayList<String>();
		list.add("a");
		String str0 = list.get(0);

		/**
		 * 迭代器也支持泛型. 迭代器的泛型应和它遍历的集合使用的泛型一致!
		 */
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}

		int[] array = { 1, 2, 3, 4, 5, 6 };

		for (int i = 0; i < array.length; i++) {
			int num = array[i];
			System.out.print(num + ",");
		}
		System.out.println();

		for (int num : array) {
			System.out.print(num + ",");
		}
		System.out.println();

		Collection<String> c = new ArrayList<String>();
		c.add("a");
		c.add("b");
		c.add("c");
		c.add("d");

		/**
		 * 新循环内部是迭代器实现. 所以使用新循环遍历集合时,不要使用集合的方法将 元素删除
		 */
		for (String str : c) {
			if ("b".equals(str)) {
				// c.remove(str);
			}
			System.out.println(str);
		}
	}

	// @Test
	public void testQueue1() {
		/**
		 * 队列,可以存储一组数据 存取元素遵循先进先出原则 LinkedList由于其设计原理,所以其也是队列的一个 实现类
		 */
		Queue<String> queue = new LinkedList<String>();
		/**
		 * 入队方法 offer()
		 */
		queue.offer("a");
		queue.offer("b");
		queue.offer(null);
		queue.offer("c");

		System.out.println(queue);
		/**
		 * 出队方法 poll() 调用poll方法后,队首元素即出队(从队列中删除)
		 */
		// String obj = queue.poll();
		/**
		 * 若仅仅是引用队首元素,不希望其出队 peek()
		 */
		String obj = queue.peek();

		System.out.println(obj);
		System.out.println(queue);
		/**
		 * 遍历队列是一次性的
		 */
		// while( (obj = queue.poll()) != null ){
		// System.out.println(obj);
		// }
		/**
		 * 注意for循环的写法 队列支持size()方法,获取当前队列的元素数量
		 */
		for (int i = queue.size(); i > 0; i--) {
			System.out.println(queue.poll());
		}

		System.out.println(queue);
	}

	// @Test
	public void testSubList() {
		List<Integer> list = new ArrayList<Integer>();
		// 向集合中添加10个数字 0-9
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		System.out.println(list);// [0,1,2,3,4,5,6,7,8,9]

		List<Integer> subList = list.subList(3, 8);

		System.out.println(subList);// [3,4,5,6,7]
		/**
		 * 将子集中的元素扩大10倍
		 */
		for (int i = 0; i < subList.size(); i++) {
			int num = subList.get(i);
			num = num * 10;
			subList.set(i, num);
		}
		// int index = 0;
		// for(int i : subList){
		// subList.set(index++, i * 10);
		// }
		/**
		 * 对子集中的任何元素进行修改,都会影响原集合
		 */
		System.out.println(subList);// [30,40,50,60,70]
		System.out.println(list);// [0,1,2,30,40,50,60,70,8,9]
	}

	// @Test
	public void testCollectionSort() {
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(1, 5));
		list.add(new Point(3, 4));
		list.add(new Point(2, 2));
		list.add(new Point(3, 3));

		System.out.println(list);// [(1,5),(3,4),(2,2)]

		/**
		 * Collections是集合的工具类 sort的参数要求传入List集合.因为其有序
		 */
		Collections.sort(list);

		System.out.println(list);
	}

	// @Test
	public void testCollectionSort2() {
		List<String> list = new ArrayList<String>();
		list.add("zark");
		list.add("mary");
		list.add("Jazz");
		list.add("Dark");
		list.add("kill");
		list.add("Able");
		list.add("Ada");
		list.add("Xiloer");
		list.add("范传奇");
		list.add("苍老师");
		list.add("曹老师");
		list.add("擦老师");
		list.add("蔡老师");

		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}

	// @Test
	public void testMyComparator1() {
		List<String> list = new ArrayList<String>();
		list.add("able");
		list.add("ada");
		list.add("Xiloer");
		list.add("Zark");
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);

		// 使用自定义比较规则并排序
		MyComparator comparator = new MyComparator();
		Collections.sort(list, comparator);
		System.out.println(list);
	}

	// @Test
	public void testMyComparator2() {
		List<String> list = new ArrayList<String>();
		list.add("abel");
		list.add("mary");
		list.add("Xiloer");
		list.add("Boss");

		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		};
		Collections.sort(list, comparator);
		System.out.println(list);
	}

	// @Test
	public void testMap1() {
		/**
		 * 需求:统计每一个数字出现的次数 思路: 1:先根据","拆分字符串,拿到每一个数字 2:循环数组,取出每一个字符串,并用其作为key
		 * 放入Map中,这时会有两种情况: 1:Map中没有这个key(说明第一次统计这个数字) 那么存放时,value放1
		 * 2:Map中存在这个key(说明以前统计过这个数字) 那么将value取出累加1后再存入 3:最终Map中就得出了所有数字出现的次数了
		 */
		// 1
		String lines = "123,4534,675,234,876,234,2348,123,5436,234,4534";

		String[] array = lines.split(",");
		// 2
		Map<String, Integer> map = new HashMap<String, Integer>();
		// [123,4534,675,234,876,234,2348,123,5436,234,4534]
		for (String str : array) {
			// 查看当前字符串作为key是否在Map中存在
			if (map.containsKey(str)) {
				// 存在说明以前统计过
				int sum = map.get(str);
				sum = sum + 1;
				map.put(str, sum);
			} else {
				map.put(str, 1);// 第一次统计
			}

		}

		System.out.println(map);
	}

	// @Test
	public void testSet1() {
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");

		System.out.println(set);
		/**
		 * 在判断是否包含时 HashSet会先将给定的元素"c"的hashcode值获取 然后进行散列算法,找到其位置后,与集合中该位置的元素
		 * 进行equals比较,若为true则表示包含. 所以由此看出,HashSet的查询效率是很高的
		 */
		if (set.contains("c")) {
			System.out.println("包含");
		} else {
			System.out.println("不包含");
		}
	}

	// @Test
	public void testSet2() {
		Set<Point> set = new HashSet<Point>();
		Point p = new Point(1, 2);
		set.add(p);
		/**
		 * 修改了p对象的属性,则hashCode()返回值就会 发生改变
		 */
		p.setX(3);
		/**
		 * 再次向HashSet集合添加该元素时,由于hashCode值发生 了变化,那么计算位置就发生了变化,这样再次存放元素
		 * 时就没有机会与上次存放时进行equals比较的机会. 所以存入了集合两次.
		 */
		set.add(p);
		set.remove(p);
		p.setX(1);
		set.remove(p);

		System.out.println(set);
	}

	// @Test
	public void testIterateSet() {
		/**
		 * HashSet 当元素不发生改变的前提下,无论我们以何种先后顺序 存入元素,元素在集合内部的位置是固定的.
		 * 我们所谓的无序,指的是我们存入元素的顺序与元素在 集合内部的顺序不一致
		 */
		Set<String> set = new HashSet<String>();
		set.add("one");
		set.add("two");
		set.add("three");
		System.out.println(set);

		/**
		 * 遍历Set集合的方式:迭代器
		 */
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + ",");
		}
		System.out.println();
		/**
		 * 新循环的方式
		 */
		for (String str : set) {
			System.out.print(str + ",");
		}
		// IterateSet is = new IterateSet();
		// System.out.println(is.hashCode());
		// System.out.println(is);
	}

	//@Test
	public void testIterateSet2() {
		// java.util.Set
		Set<Integer> set = new HashSet<Integer>();

		Random r = new Random();
		int sum = 0;// 记录循环次数
		while (set.size() < 20) {
			set.add(r.nextInt(20));
			sum++;
		}
		System.out.println("生成了:" + sum + "个数字");
		System.out.println(set);
	}

//	@Test
	public void testMap() {
		/**
		 * 模拟菜谱
		 */
		Map<String, String> map = new HashMap<String, String>();

		/**
		 * 向Map中存放元素
		 */
		String oldValue = map.put("鸡蛋", "3个");
		System.out.println(oldValue);
		/**
		 * 使用重复的key存放value,会将之前的value替换掉 并作为这次put方法的返回值返回给我们
		 */
		oldValue = map.put("鸡蛋", "4个");
		System.out.println(oldValue);

		map.put("番茄", "1个");
		map.put("糖", "3g");
		map.put("盐", "1个");
		map.put("葱", "少许");

		System.out.println(map);

		/**
		 * 获取key为"葱"所对应的值
		 */
		String value = map.get("葱");
		System.out.println("葱:" + value);
	}
	@Test
	public void testList(){
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		System.out.print("[");
		for(int i=0;i<list.size();i++){
			System.out.print(i+1 + ",");
			System.out.print(list.get(i));
			System.out.print(i==list.size()-1?"":",");
		}
		System.out.print("]");
	}
}

/**
 * Comparator接口 用于额外的定义比较大小的规则 用途: 当我们想排序的元素已经实现了比较规则,而这种比较规则
 * 又不满足我们对排序的需求时,我们可以定义一个类并实现 Comparator.从而单独定义一种比较规则,用来满足我们对 排序的需求
 * 
 * @author Administrator
 * 
 */
class MyComparator implements Comparator<String> {
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}

}
