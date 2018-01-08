package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试常量池溢出   -XX:MetaspaceSize=2m -XX:MaxMetaspaceSize=2m
 * 输出结果如下：
 * Error occurred during initialization of VM
 * OutOfMemoryError: Metaspace
 *
 * @author chenjie
 * @create 2017-10-10 14:45
 */
public class RunTimeConstantPoolOOM {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
			System.out.println(i);
		}
	}
}
