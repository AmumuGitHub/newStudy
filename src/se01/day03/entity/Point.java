package se01.day03.entity;

/**
 * @author amumu
 * @date 2016上午01:19:29
 */
/**
 * 自定义的一个类.用于作为存入集合使用的测试类
 * 
 * @author Administrator
 * 
 */
public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			return this.x == p.x && this.y == p.y;
		}
		return false;
	}

	/**
	 * 格式 (x,y)
	 */
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
