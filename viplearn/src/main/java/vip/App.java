package vip;

import com.alibaba.fastjson.JSON;
import com.sun.nio.zipfs.ZipFileAttributes;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{

	/**
	 * 测试类加载器相关
	 * @param args
	 */
	public static void main(String[] args) {
		// Object 类在 <java_home>/jre/lib/rt.jar 中，
		// 由 Bootstrap ClassLoader 加载，由于该类加载器是由 native code 编写
		// 所以输出为 null
		Object[] objects = new Object[5];
		System.out.println();
		System.out.println(objects.getClass().getClassLoader());

		// ZipFileAttributes 类在 <java_home>/jre/lib/ext/zipfs.jar 中，
		// 由 Extension ClassLoader 加载，
		// 输出为  sun.misc.Launcher$ExtClassLoader@4b67cf4d
		ZipFileAttributes[] attributes = new ZipFileAttributes[5];
		System.out.println();
		System.out.println(attributes.getClass().getClassLoader());

		// Main 类是自定义的类，
		// 默认由 System ClassLoader 加载，
		// 输出为 sun.misc.Launcher$AppClassLoader@18b4aac2
		App[] array = new App[5];
		array[0] = new App();
		System.out.println();
		System.out.println(array.getClass().getClassLoader());
	}
	/**
	 * 声明式代码
	 */
	@Test
    public void m1() {
    	int[] iArr = {1,2,3,4,5};
	    Arrays.stream(iArr).forEach(System.out::println);
    }

	@Test
	public void m2() {
		String[] strs = {"2222", "33333"};
		Arrays.sort(strs, String::compareToIgnoreCase);
		Stream<String> stream = Stream.of(strs);
	}

	@Test
	public void flatMap() {
		Stream<List<Integer>> inputStream = Stream.of(
				Arrays.asList(1),
				Arrays.asList(2, 3),
				Arrays.asList(4, 5, 6)
		);
		Stream<Integer> outputStream = inputStream.
				flatMap((childList) -> childList.stream());
		List<Integer> collect = outputStream.map(Function.identity()).collect(Collectors.toList());
		System.out.println(JSON.toJSONString(collect));
	}
}
