package java24.jvm;

/**
 * 测试类加载
 */
public class TestClassLoad {

	public static void main(String[] args) throws Exception {

		Class.forName("com.it18zhang.java24.jvm.Person", false, TestClassLoad.class.getClassLoader());
//		Person p = new Person();
//		p = new Person();
//		p = new Person();
//		p = new Person();
//		System.out.println(p.getName());
//		System.out.println("yyy");
	}
}
