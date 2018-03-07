package dp;

import org.junit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: chenjie
 * @date: 2018/2/7
 */
public class Test1 {

	public static String replace(String str) {
		char[] chars = str.toCharArray();
		int count = 0;
		int length = chars.length;
		int[] tag = new int[length];
		for (int i = 0; i < length; i++) {
			if (chars[i] == 'a' && (i + 2) < length && chars[i + 1] == 'b' && chars[i + 2] == 'c') {
				count++;
				tag[i] = 1;
			}
		}
		System.out.println(count);
		char[] res = new char[length - 3 * count];
		int tmp = 0;
		for (int i = 0; i < length; ) {
			if (tag[i] == 1) {
				i = i + 3;
			} else {
				res[tmp++] = chars[i++];
			}
		}
		return new String(res);
	}

	@org.junit.Test
	public void m1() {
		String str = "labcdef";
//		String replace = replace(str);
		String replace = replace2(str);
		System.out.println(replace);
	}

	public static String replace2(String str) {
		StringBuilder res = new StringBuilder();
		int length = str.length();
		int count = 0;
		for (int i = 0; i < length; ) {
			if (str.charAt(i) == 'a' && (i + 2) < length && str.charAt(i + 1) == 'b' && str.charAt(i + 2) == 'c') {
				i = i + 3;
				count++;
			} else {
				res.append(str.charAt(i++));
			}
		}
		System.out.println(count);
		return res.toString();
	}

	@org.junit.Test
	public void m13() {
		Map<String, Integer> map = new HashMap<String, Integer>(8);
		map.put("aaa", 1);
		map.put("bbb", 2);
		map.put("ccc", 3);
		map.put("ddd", 4);
		map.put("eee", 5);
		map.put("fff", 6);
		map.put("ggg", 7);
		map.put("hhh", 8);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-->" + entry.getValue());
		}
	}

	@org.junit.Test
	public void m18() {
		String a= "abc";
		String b = "abc";
		String c = new String("abc");
		String d = "ab" + "c";
		System.out.println(a == b);   //true
		System.out.println(a == c);   //false
		System.out.println(a == d);   //true

	}


}
