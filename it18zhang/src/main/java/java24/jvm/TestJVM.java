package java24.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TestJVM {

	public static void main(String[] args) {
		int max = 1024 * 1024 * 10;
		//
		List<byte[]> list = new ArrayList<byte[]>();
		//
		for (; ; ) {
			list.add(new byte[max]);
			list.add(new byte[max]);
			list.add(new byte[max]);
			list.add(new byte[max]);
		}
	}

	/**
	 * 测试堆
	 */
	@Test
	public void testHeap() {
		int max = 1024 * 1024 * 10;
		//
		List<byte[]> list = new ArrayList<byte[]>();
		//
		for (; ; ) {
			list.add(new byte[max]);
			list.add(new byte[max]);
			list.add(new byte[max]);
			list.add(new byte[max]);
		}
	}

	/**
	 * 测试栈溢出
	 */
	@Test
	public void testStack() {
		print(1);
	}

	/**
	 *
	 */
	public void print(int x) {
		System.out.println(x);
		if (x % 1000 == 0) {
			System.out.println();
		}
		print(x + 1);
	}


}
