package dp;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * java传值和传引用
 * java finally修改值
 *
 * @author: chenjie
 * @date: 2017/9/24
 */
public class AAA {

	public static void main(String[] args) {
		System.out.println(test());
		System.out.println(test4());
		System.out.println(changeBBB());
	}

	/**
	 * finally块的语句在try或catch中的return语句执行之后返回之前执行
	 * 且finally里的修改语句可能影响也可能不影响try或catch中return
	 * 已经确定的返回值，如果返回值类型为传址类型，则影响；传值类型，则不
	 * 影响。若finally里也有return语句则覆盖try或catch中的return语句直接返回。
	 *
	 * @return
	 */
	public static Map<String, String> test() {
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("key1", "v1");
		try {
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			map.put("key2", "v2");
			map = null;
		}
		return map;
	}

	public static BBB changeBBB() {
		BBB bbb = new BBB();
		bbb.setAge(1);
		bbb.setName("name1");

		try {
			bbb.setName("name2");
			return bbb;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bbb.setAge(2);
			bbb = null;
		}
		return bbb;

	}

	/**
	 * 测试 try catch finally对返回值的影响
	 * @return
	 */
	public static int test4() {
		int b = 20;
		try {
			System.out.println("try block");
			b = b / 0;
			return b += 80;
		} catch (Exception e) {
			b += 15;
			System.out.println("catch block");
		} finally {
			System.out.println("finally block");
			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}
			b += 50;
		}
		return 204;
	}

	static class BBB {
		private int age;
		private String name;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "BBB{" +
					"age=" + age +
					", name='" + name + '\'' +
					'}';
		}
	}

	/**
	 * 利用iterator的remove可以避免快速失败
	 */
	@Test
	public void m1() {
		Set<String> set = new HashSet<String>(3) {{
			add("111");
			add("222");
			add("333");
		}};
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String val = iterator.next();
			if (val.equals("222")) {
				iterator.remove();
				System.out.println("找到了");
			}
			System.out.println(val);
		}
	}


	/**
	 * trimToSize 在所有的添加完成后调用以避免浪费空间
	 */
	@Test
	public void m12() {
		ArrayList<Integer> list = new ArrayList<Integer>(6);
		list.add(1);
		list.add(2);
		list.trimToSize();

	}

	/**
	 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
	 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 */
	@Test
	public void m2() {
		Solution solution = new Solution();
		String s = solution.replaceSpace(new StringBuffer("We  Are Happy"));
		System.out.println(s);
	}

	public class Solution {
		public String replaceSpace(StringBuffer str) {
			String s = str.toString();
			return s.replaceAll(" ", "%20");
		}

		public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
			Stack<Integer> stack = new Stack<Integer>();
			while (listNode != null) {
				stack.push(listNode.val);
				listNode = listNode.next;
			}

			ArrayList<Integer> list = new ArrayList<Integer>();
			while (!stack.isEmpty()) {
				list.add(stack.pop());
			}
			return list;
		}

	}

	@Test
	public void m3() {
		String str = "select * from";
		String[] split = str.split(";");
		System.out.println(split.length);
		for (String sql : split) {
			System.out.println(sql);
		}
	}

	@Test
	public void m4() {
		Map<String, String> map = new HashMap<String, String>() {{
			put("key1", "val1");
			put("key2", "val2");
			put("key3", "val3");
		}};
		System.out.println(map.toString());
	}

	@Test
	public void m5() {
		HiveAuthRecord hiveAuthRecord = new HiveAuthRecord("db1", "tb1");
		HiveAuthRecord hiveAuthRecord1 = new HiveAuthRecord("db2", "tb2");
		HiveAuthRecord hiveAuthRecord2 = new HiveAuthRecord("db1", "tb1");
		Map<HiveAuthRecord,Integer> map = new HashMap<HiveAuthRecord, Integer>();
		map.put(hiveAuthRecord,1);
		map.put(hiveAuthRecord1,1);
		map.put(hiveAuthRecord2,1);
		System.out.println(map.size());

	}


}

class HiveAuthRecord {

	private String db;
	private String table;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HiveAuthRecord that = (HiveAuthRecord) o;

		if (!db.equals(that.db)) return false;
		return table.equals(that.table);
	}

	@Override
	public int hashCode() {
		return this.db.hashCode() + this.table.hashCode();
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public HiveAuthRecord(String db, String table) {
		this.db = db;
		this.table = table;
	}
}

