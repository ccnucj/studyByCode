package dp;

import org.junit.Test;

import java.util.Random;

/**
 * 快速排序算法
 *
 * @author: chenjie
 * @date: 2017/12/2
 */
public class Sort {

	/**
	 * 快速排序
	 */
	public static int getMiddle(int[] number, int low, int high) {
		int temp = number[low];
		while (low < high) {
			while (low < high && number[high] >= temp) {
				high--;
			}
			number[low] = number[high];
			while (low < high && number[low] <= temp) {
				low++;
			}
			number[high] = number[low];
		}
		number[low] = temp;
		return low;
	}

	public static void quickSort(int[] number, int low, int high) {
		if (low < high) {
			int middle = getMiddle(number, low, high);
			quickSort(number, low, middle - 1);
			quickSort(number, middle + 1, high);
		}
	}

	public static void quick(int[] number) {
		if (number.length > 0) {
			quickSort(number, 0, number.length - 1);
		}
	}

	@Test
	public void m1() {
		int[] numer = new int[5];
		for (int i = 0; i < 5; i++) {
			Random random = new Random();
			numer[i] = random.nextInt(10);
		}
		for (int n : numer) {
			System.out.print(n);
		}
		System.out.println();
		quick(numer);
		for (int n : numer) {
			System.out.print(n);
		}
	}

}
