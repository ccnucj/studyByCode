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


	/**
	 * 插入排序
	 */
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int tmp = arr[i];
			int j = i;
			for (; j > 0 && tmp < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = tmp;
		}
	}

	@Test
	public void m2() {
		int[] arr = {1, 3, 2, 5, 4, 6, 9, 7, 8};
		insertionSort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	/**
	 * 希尔排序
	 *
	 * @param arr
	 */
	public static void shellSort(int[] arr) {
		for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
			for (int i = gap; i < arr.length; i++) {
				int tmp = arr[i];
				int j;
				for (j = i; j >= gap && tmp < arr[j - gap]; j = j - gap) {
					arr[j] = arr[j - gap];
				}
				arr[j] = tmp;
			}
		}
	}

	@Test
	public void m3() {
		int[] arr = {1, 3, 2, 5, 4, 6, 9, 7, 8};
		shellSort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	/**
	 * 堆排序
	 *
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		//构建大顶堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			percDown(arr, i, arr.length);
		}
		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);
			percDown(arr, 0, i);
		}
	}

	public static int leftChild(int i) {
		return 2 * i + 1;
	}

	public static void percDown(int[] arr, int i, int n) {
		int child = -1;
		int tmp;
		for (tmp = arr[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && arr[child] < arr[child + 1]) {
				child++;
			}
			if (tmp < arr[child]) {
				arr[i] = arr[child];
			} else break;
		}
		arr[i] = tmp;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * 测试堆排序
	 */
	@Test
	public void m4() {
		int[] arr = {1, 3, 2, 5, 4, 6, 9, 7, 8};
		heapSort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	/**
	 * 归并排序
	 */
	@Test
	public void m5() {
		int[] arr = {1, 3, 2, 5, 4, 6, 9, 7, 8};
		mergeSort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}


	private static void mergeSort(int[] arr) {
		int[] tmp = new int[arr.length];
		mergeSort(arr, tmp, 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int[] tmp, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, tmp, left, mid);
			mergeSort(arr, tmp, mid + 1, right);
			merge(arr, tmp, left, mid, right);
		}
	}

	private static void merge(int[] arr, int[] tmp, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int t = left;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				tmp[t++] = arr[i++];
			} else {
				tmp[t++] = arr[j++];
			}
		}
		while (i <= mid) {
			tmp[t++] = arr[i++];
		}
		while (j <= right) {
			tmp[t++] = arr[j++];
		}

		t = left;
		//将temp中的元素全部拷贝到原数组中
		while (left <= right) {
			arr[left++] = tmp[t++];
		}
	}


}
