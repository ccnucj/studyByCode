package dp;

import org.junit.*;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author: chenjie
 * @date: 2018/3/4
 */
public class Test2 {

	@org.junit.Test
	public void m1() {
		StringBuilder res = new StringBuilder();
		String str = "";
		char[] chars = str.toCharArray();
		for (int i = 0;i< chars.length; i++){
			if (chars[i] == ' '){
				res.append("%20");
			} else {
				res.append(chars[i]);
			}

		}
	}

	@Test
	public void m2() {
		int i = 0;
		if(i++ >= 0){
			System.out.println(">=0");
		}
		else if(i++ >=1){
			System.out.println(">=1");
		}
		System.out.println(i);

	}



	public static int min(int[] numbers) {
		// 判断输入是否合法
		if (numbers == null || numbers.length == 0) {
			throw new RuntimeException("Invalid input.");
		}

		// 开始处理的第一个位置
		int lo = 0;
		// 开始处理的最后一个位置
		int hi = numbers.length - 1;
		// 设置初始值
		int mi = lo;
		int count = 0;

		// 确保lo在前一个排好序的部分，hi在排好序的后一个部分
		while (numbers[lo] >= numbers[hi]) {
			System.out.println("count = " + (++count));
			// 当处理范围只有两个数据时，返回后一个结果
			// 因为numbers[lo] >= numbers[hi]总是成立，后一个结果对应的是最小的值
			if (hi - lo == 1) {
				return numbers[hi];
			}

			// 取中间的位置
			mi = lo + (hi - lo) / 2;

			// 如果三个数都相等，则需要进行顺序处理，从头到尾找最小的值
			if (numbers[mi] == numbers[lo] && numbers[hi] == numbers[mi]) {
				return minInorder(numbers, lo, hi);
			}

			// 如果中间位置对应的值在前一个排好序的部分，将lo设置为新的处理位置
			if (numbers[mi] >= numbers[lo]) {
				lo = mi;
			}
			// 如果中间位置对应的值在后一个排好序的部分，将hi设置为新的处理位置
			else if (numbers[mi] <= numbers[hi]) {
				hi = mi;
			}
		}

		// 返回最终的处理结果
		return numbers[mi];
	}

	/**
	 * 找数组中的最小值
	 *
	 * @param numbers 数组
	 * @param start   数组的起始位置
	 * @param end     数组的结束位置
	 * @return 找到的最小的数
	 */
	public static int minInorder(int[] numbers, int start, int end) {
		int result = numbers[start];
		for (int i = start + 1; i <= end; i++) {
			if (result > numbers[i]) {
				result = numbers[i];
			}
		}
		return result;
	}

	@Test
	public void m3() {
		int[] array1 = {1, 2, 3, 4, 5};
		min(array1);
	}

	@Test
	public void m4() {
		int n = 9;
		System.out.println(numberOfOne(9));
	}

	public static int numberOfOne(int n) {
		// 记录数字中1的位数
		int result = 0;

		// JAVA语言规范中，int整形占四个字节，总计32位
		// 对每一个位置与1进行求与操作，再累加就可以求出当前数字的表示是多少位1
		for (int i = 0; i < 32; i++) {
			result += (n & 1);
			n >>>= 1;
		}

		// 返回求得的结果
		return result;
	}
}
