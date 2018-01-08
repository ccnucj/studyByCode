package dp;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dp.Shape.findMax;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	/**
	 * 可以利用此机制，实现权限的控制，即位相或
	 */
	@Test
	public void m1() {

		Modifier.isPublic(15);
	}

	@Test
	public void m2() throws Exception {
		Class clazz = Class.forName("dp.AAA");
		Constructor declaredConstructor = clazz.getDeclaredConstructor(int.class);
		AAA aaa = (AAA) declaredConstructor.newInstance(123);
	}

	/**
	 * 使用函数对象
	 */
	@Test
	public void m11() {
		Shape[] shapes = {
				new Shape(123, 123),
				new Shape(1234, 1234),
				new Shape(1235, 1235),
		};
		Shape max = findMax(shapes, new CompareImpl());
		System.out.println(max.getArea() + "," + max.getLength());
	}

	/**
	 * map中是否存在key
	 * 若map中不存在null值，则get返回的值可以用来确定key是否在map中
	 * 然而，如果存在null值，则必须使用containsKey
	 */
	@Test
	public void m12() {
		Map<String, String> map = new HashMap<String, String>() {{
			put("11",null);
			put("22","2222");
			put("33","3333");
		}};
		System.out.println(map.get("11"));
		System.out.println(map.containsKey("11"));

	}

	@Test
	public void m13() {
		final List<String> list = new ArrayList<String>() {{
			add("tb1");
			add("tb2");
			add("tb3");
			add("tb4");
		}};
		final List<String> list1 = new ArrayList<String>() {{
			add("tb5");
			add("tb6");
			add("tb7");
			add("tb8");
		}};
		Map<String,List<String>> map = new HashMap<String, List<String>>() {{
			put("db1",list);
			put("db2",list1);
		}};
		System.out.println(map.toString().replaceAll("=",""));
	}

	@Test
	public void m14() {
		BBB bbb = new BBB();
		bbb.setKey("vvvvv");
		String key = ((Map<String, String>) bbb).get("key");
		System.out.println(key);


	}


}

class BBB {
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
