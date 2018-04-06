package chenjie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class L022_Generate_Parentheses {

	public static List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		doGenerate(result, new StringBuilder(), 0, 0, n);
		return result;
	}

	private static void doGenerate(List<String> result, StringBuilder sb, int leftNum, int rightNum, int n) {
		if (leftNum == n && rightNum == n) {
			result.add((new StringBuilder(sb)).toString());
			return;
		}
		if (leftNum == rightNum) {
			sb.append('(');
			doGenerate(result, sb, leftNum + 1, rightNum, n);
			sb.deleteCharAt(sb.length() - 1);
		} else {
			if (leftNum < n) {
				sb.append('(');
				doGenerate(result, sb, leftNum + 1, rightNum, n);
				sb.deleteCharAt(sb.length() - 1);
			}

			if (rightNum < n) {
				sb.append(')');
				doGenerate(result, sb, leftNum, rightNum + 1, n);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	@Test
	public void m1() {
		List<String> strings = generateParenthesis(3);
		System.out.println(strings);
	}


}
