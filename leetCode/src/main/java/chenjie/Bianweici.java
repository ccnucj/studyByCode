package chenjie;

import org.junit.Test;

/**
 * 子串的变位词
 *
 * @author: chenjie
 * @date: 2018/4/2
 */
public class Bianweici {
	static boolean f(char[] a, char[] b) {
		int[] num = new int[26];
		int nonZero = 0;
		for (int i = 0; i < b.length; ++i) {
			if (++num[b[i] - 'a'] == 1) ++nonZero;
		}
		for (int i = 0; i < b.length; ++i) {
			int c = a[i] - 'a';
			--num[c];
			if (num[c] == 0) --nonZero;
			else if (num[c] == -1) ++nonZero;
		}
		if (nonZero == 0) return true;
		//旧窗口a[i-b.size()..i-1]
		//新窗口a[i-b.size()+1..i]
		for (int i = b.length; i < a.length; ++i) {
			//删除a[i-b.size()]
			int c = a[i - b.length] - 'a';
			++num[c];
			if (num[c] == 0) --nonZero;
			else if (num[c] == 1) ++nonZero;
			//添加a[i]
			c = a[i] - 'a';
			--num[c];
			if (num[c] == 0) --nonZero;
			else if (num[c] == -1) ++nonZero;
			if (nonZero == 0) return true;
		}
		return false;
	}

	@Test
	public void m1() {
		String a = "ell";
		char[] charsA = a.toCharArray();
		String b = "lel";
		char[] charsB = b.toCharArray();
		System.out.println(f(charsA,charsB));

	}

}
