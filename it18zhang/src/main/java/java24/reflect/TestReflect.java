package java24.reflect;

import java24.jvm.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestReflect {
	/**
	 * 测试Class
	 */
	@Test
	public void testClass() {
//		Person p = new Person();
//		Class clazz = p.getClass();
//		Class clazz2 = Person.class ;
//		
//		System.out.println(clazz.hashCode());
//		System.out.println(clazz2.hashCode());
//		
//		System.out.println(clazz.getName());
	}

	/**
	 * 动态访问对象的属性和方法。
	 * 反射的出发点是类，方法，字段。
	 */
	@Test
	@SuppressWarnings({"rawtypes", "unused"})
	public void testNewInstance() throws Exception {
		//加载类,返回Class对象
		Class clazz = Class.forName("com.it18zhang.java24.jvm.Person");

		//通过Class动态创建对象.
		Object obj = clazz.newInstance();

		//得到setName(String x);
		Method m = clazz.getDeclaredMethod("setName", String.class, String.class);

		//设置可访问性
		m.setAccessible(true);

		//等价于obj.setName("tom");
		m.invoke(obj, "t", "om");

		System.out.println("xxx");

		//动态取得getName方法
		Method m2 = clazz.getDeclaredMethod("getName");
		Object ret = m2.invoke(obj);
		System.out.println(ret);

		//Field,字段描述符
		Field f = clazz.getDeclaredField("name");
		f.setAccessible(true);
		ret = f.get(obj);
		System.out.println(ret);

		f.set(obj, "jerry");

		System.out.println("xxx");

		//得到所有可用的方法
		Method[] ms = clazz.getMethods();
		for (Method mm : ms) {
			String fname = mm.getName();
			Class[] ptypes = mm.getParameterTypes();
			if (fname.startsWith("get") && (ptypes == null || ptypes.length == 0)) {
				//修饰符总和
				int mod = mm.getModifiers();
				System.out.println(Modifier.isPublic(mod));

				ret = mm.invoke(obj);
				System.out.println(fname + " = " + ret);
			}
			//System.out.println(mm.toString());
		}
//		System.out.println("===================");
//		//得到该类声明的方法
//		ms = clazz.getDeclaredMethods();
//		for(Method mm : ms){
//			System.out.println(mm);
//		}
	}

	/**
	 * Constructor，构造函数描述符
	 */
	@Test
	public void testConstructor() throws Exception {
		Class vc = void.class;
		System.out.println(vc.isPrimitive());

		Class clazz = Class.forName("com.it18zhang.java24.jvm.Person");
		Constructor c1 = clazz.getDeclaredConstructor(String.class, int[][][][].class);
		c1.setAccessible(true);
		Object obj = c1.newInstance("tom", 12);
		System.out.println(obj);

		Method[] ms = clazz.getMethods();
		for (Method mm : ms) {
			String fname = mm.getName();
			Class rtype = mm.getReturnType();
			if (rtype == void.class) {
				System.out.println(fname);
			}
		}
	}

	@Test
	public void testCopy() throws Exception {
		Person a = new Person();
		a.setName("tom");
		a.setAge(12);

		Person b = new Person();
		propCopy(a, b);
		System.out.println("xx");
	}

	/**
	 * 属性复制
	 */
	public void propCopy(Person a, Person b) throws Exception {
		Class clazz = a.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);
			Object ret = f.get(a);
			f.set(b, ret);
		}
	}
}
