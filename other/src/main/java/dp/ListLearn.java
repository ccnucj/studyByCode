package dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * list中不常用到的方法
 *
 * @author: chenjie
 * @date: 2017/12/31
 */
public class ListLearn {

	/**
	 * ListIterator 使用
	 */
	@Test
	public void m1() {
		List<Integer> list = new ArrayList<Integer>() {{
			add(1);
			add(2);
			add(3);
			add(4);
			add(5);
			add(6);
		}};
		ListIterator<Integer> iterator = list.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("========================");
		while (iterator.hasPrevious()) {
			System.out.println(iterator.previous());
		}

	}

	@Test
	public void m2() {
		List<Integer> list = new ArrayList<Integer>() {{
			add(1);
			add(2);
			add(3);
			add(4);
			add(5);
			add(6);
		}};
		Iterator<Integer> iterator = list.iterator();
		if (iterator.hasNext()) {
			iterator.next();
			iterator.next();
			iterator.next();
			iterator.remove();
			iterator.remove();
		}
		System.out.println(list);
	}

}
