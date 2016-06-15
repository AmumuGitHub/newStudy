package se01.day05.entity;
/**
 * 模板模式
 * 模板通常都是抽象类
 * @author Administrator
 *
 */
public abstract class Person {
	/**
	 * 介绍自己
	 */
	public void sayHello(){
		//问好
		System.out.println("大家好!");
		//说名字
		System.out.println("我叫:"+ getName());
		//说自己的情况
		System.out.println(getInfo());
		//告别
		System.out.println("谢谢大家!多多关照!");
	}
	
	public abstract String getInfo();
	//获取自己的名字
	public abstract String getName();
}


