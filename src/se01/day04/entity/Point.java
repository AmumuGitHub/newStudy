package se01.day04.entity;
/**
 * 表示坐标系上的一个点
 * 测试可比较性
 * 
 * 一个类要想变为可比较的类应实现Comparable接口
 * 通常实现接口时,我们会为Comparable指定泛型,而类型就是
 * 当前类
 * @author Administrator
 *
 */
public class Point implements Comparable<Point>{
	private int x;
	private int y;
	
	public int compareTo(Point o) {
		int r = this.x * this.x + this.y * this.y;
		int r1 = o.x * o.x + o.y * o.y;		
		
		return r - r1;
	}
	/**
	 * 1+1=2
	 * 4+4
	 **/
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
	

	
	
}






