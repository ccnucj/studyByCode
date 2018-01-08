package dp;

/**
 * 1.初始化c2()时，先初始化c2的父类：c1()
 * 初始化c1()的静态变量和静态代码块
 * 初始化c2()的静态变量和静态代码块
 * 2.初始化c1()的非静态变量，初始化父类的构造函数。
 * 如果父类的构造函数中调用的方法被子类重写，那么调用的会是子类的方法。
 * 3.初始化c2()的非静态变量，初始化子类的构造函数。
 */
public class C {
	public static void main(String[] args) {
		new c2();
	}

}

class c1 {
	String name = "aaaaaa";

	//父类构造函数
	public c1() {
		print();
	}

	//该构造函数自始至终就没有被调用过
	public void print() {
		System.out.println(name);
	}
}

class c2 extends c1 {
	String name = "asdg";

	//构造函数：
	public c2() {
		System.out.println(name);
	}

	//重写父类中的print（）函数
	public void print() {
		System.out.println(name);
	}
}
