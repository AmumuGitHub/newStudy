package se01.day05;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;

/**
 * @author amumu
 * @date 2016下午04:02:18
 */
public class Day05 {
	// @Test
	public void testIterator() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("鸡蛋", "3个");
		map.put("西红柿", "1个");
		map.put("盐", "3g");
		map.put("糖", "1g");
		map.put("鸡精", "少许");
		map.put("油", "3g");

		/**
		 * 遍历Map中所有的key Set keySet() 获取当前Map中所有的key值,并存入集合返回
		 * 集合的泛型应该和Map中定义的key的类型一致
		 */
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.print("key:" + key + ",");
		}
		System.out.println();
		/**
		 * 遍历Map中的每一组键值对 Entry的每一个实例描述一组键值对
		 */
		// java.util.Map.Entry
		Set<Entry<String, String>> entrySet = map.entrySet();
		// 获取迭代器
		Iterator<Entry<String, String>> entryIt = entrySet.iterator();
		// 使用新循环
		for (Entry<String, String> entry : entrySet) {
			String key = entry.getKey();
			String value = entry.getValue();

			System.out.print(key + ":" + value + ",");

		}
		System.out.println();
		/**
		 * 遍历Map中的所有value 不常用
		 */
		Collection<String> values = map.values();

		for (String value : values) {
			System.out.print("value:" + value);
		}
	}

	// @Test
	public void testException1() {
		/**
		 * try语句,用于将可能出现异常的代码片段包围起来 用来发现程序执行过程中出现的异常 try{ 可能出现异常的代码片段 }
		 */
		try {

			String str = "aaaaaa";
			// 这里会出现空指针异常
			System.out.println(str.length());
			// 出现字符串下标越界
			System.out.println(str.charAt(10));
			// 出现数字格式异常
			System.out.println(Integer.parseInt(str));

		} catch (NullPointerException e) {
			System.out.println("出现了空指针!");

		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("出现了字符串下标越界");
			throw e;// 抛出就是抛给调用者处理，如果没用调用者，就直接抛给容器，不往下执行了
		} catch (Exception e) {
			System.out.println("出现了一个未知异常");
		}
		System.out.println("程序结束了");
	}

	// @Test
	public void testException2() throws Exception {
		try {
			String str = "10 ";
			int num = parse(str);
			System.out.println("数字为:" + num);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("程序结束了");
	}

	public static int parse(String str) {
		try {
			int num = Integer.parseInt(str);
			return num;
		} catch (RuntimeException e) {
			System.out.println("记录log日志");
			throw e;
		}
	}

	/**
	 * RuntimeException的特殊: RuntimeException及其子类成为为检查异常. 即:
	 * 我们在编译程序时,若发现有这类异常抛出时,可以在该方法 声明时忽略此类异常的抛出
	 * 
	 * 除此之外的所有异常,在方法中抛出,都需要在该方法声明时 以throws的形式定义该类异常的抛出.
	 * 
	 * RuntimeException的常见子类: NullPointerException
	 * ArrayIndexOutOfBoundsException ClassCastException NumberFormatException
	 * 查阅API
	 * 
	 * @author Administrator
	 */
	// @Test
	public void testException3() throws Exception {
		Person p = new Person();
		try {
			p.setAge(10000);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		p.sayHello();
	}

	class Person {
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			if (age < 0 || age > 100) {
				throw new RuntimeException("不是人类");
			}
			this.age = age;
		}

		public void sayHello() {
			System.out.println("大家好!我今年:" + age + "岁");
		}
	}

	// @Test
	public void testException4() throws Exception {
		System.out.println(test(null) + "," + test("0") + "," + test(""));
	}

	@Test
	// 测试二叉树
	public void testBinaryTree() {
		int[] array = { 12, 76, 35, 22, 16, 48, 90, 46, 9, 40 };
		BinaryTree root = new BinaryTree(array[0]); // 创建二叉树
		for (int i = 1; i < array.length; i++) {
			root.insert(root, array[i]); // 向二叉树中插入数据
		}
		System.out.println("先根遍历：");
		preOrder(root);
		System.out.println();
		System.out.println("中根遍历：");
		inOrder(root);
		System.out.println();
		System.out.println("后根遍历：");
		postOrder(root);
	}

	public static void preOrder(BinaryTree root) { // 先根遍历
		if (root != null) {
			System.out.print(root.data + "-");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public static void inOrder(BinaryTree root) { // 中根遍历

		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + "--");
			inOrder(root.right);
		}
	}

	public static void postOrder(BinaryTree root) { // 后根遍历
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + "---");
		}
	}

	public static int test(String str) {
		try {
			return str.charAt(0) - '0';
		} catch (NullPointerException e) {
			return 1;
		} catch (RuntimeException e) {
			// e.printStackTrace();
			return 2;
		} catch (Exception e) {
			return 3;
		} finally {
			/**
			 * finally因为会强制执行,所以不要将返回值定义写在 finally中.
			 */
			// return 4;
		}
	}
}

class BinaryTree {

	int data; // 根节点数据
	BinaryTree left; // 左子树
	BinaryTree right; // 右子树

	public BinaryTree(int data) // 实例化二叉树类
	{
		this.data = data;
		left = null;
		right = null;
	}

	public void insert(BinaryTree root, int data) { // 向二叉树中插入子节点
		if (data > root.data) // 二叉树的左节点都比根节点小
		{
			if (root.right == null) {
				root.right = new BinaryTree(data);
			} else {
				this.insert(root.right, data);
			}
		} else { // 二叉树的右节点都比根节点大
			if (root.left == null) {
				root.left = new BinaryTree(data);
			} else {
				this.insert(root.left, data);
			}
		}
	}
}