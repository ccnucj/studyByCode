package dp;

import java.util.*;

/**
 * @author: chenjie
 * @date: 2018/1/16
 */
public class Test {

	/**
	 * PECS原则
	 */
	@org.junit.Test
	public void m1() {
		List<? extends Child1> list = new ArrayList<Child2>() {{
			add(new Child2("child2_111"));
			add(new Child2("child2_112"));
			add(new Child2("child2_113"));
		}};
		for (Child1 child1 : list) {
			System.out.println(child1);
		}

		List<? super Child1> list1 = new ArrayList<Base1>();
		Collections.copy(list1, list);
	}

	/**
	 * 泛型擦除
	 */
	@org.junit.Test
	public void m2() {
		//part1
		Object[] array = new Long[1];
		array[0] = "this is String";
	}

	@org.junit.Test
	public void m3() {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList list1 = list;
		list1.add(111);
		System.out.println(list1.get(0));
	}

	@org.junit.Test
	public void m4() {
		String str1 = "INFO  : Status: Running (Executing on YARN cluster with App id application_1512979089388_2445531)";
		String str2 = "INFO  : Status: Running (Executing on YARN cluster with App id application_1505357778715_15346)";
		final String YARN_APP_ID = "Executing on YARN cluster with App id ";
		int index = str1.indexOf(YARN_APP_ID);
		String substring = str1.substring(index + YARN_APP_ID.length(), str1.indexOf(")",index));
		System.out.println(substring + "长度为" + substring.length());
		int index2 = str2.indexOf(YARN_APP_ID);
		String substring1 = str2.substring(index2 + YARN_APP_ID.length(), str2.indexOf(")",index2));
		System.out.println(substring1 + "长度为" + substring1.length());
	}

	@org.junit.Test
	public void m5() {
		int[] data = {1,2,3,4,5};
//		List<int[]> dataList = Arrays.asList(data);
		List dataList = Arrays.asList(data);
		System.out.println(dataList.size());
	}

	@org.junit.Test
	public void m6() {
		List<String> list = new ArrayList<String>();
		list.add("android");
		list.add("java");

		List<String> list1 = new ArrayList<String>(list);
		System.out.println(list);
		System.out.println(list1);

		List<String> list2 = list.subList(0,list.size());
		list2.add("unixC");

		System.out.println(list);
		System.out.println(list1);
		System.out.println(list2);

		System.out.println(list.equals(list1));
		System.out.println(list.equals(list2));
	}

	@org.junit.Test
	public void m7() {
		List<String> list = new ArrayList<String>() {{
			add("str1");
			add("str2");
			add("str3");
			add("str3");
		}};
		Set<String> set = new HashSet(list);
		list.clear();
		list.addAll(set);
		System.out.println(list);

	}
}

class ConstruClass {
	public ConstruClass() {
	}
}
