package chenjie;

import org.junit.Test;

import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author: chenjie
 * @date: 2018/4/2
 */
public class Test1 {

	public static List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length < 3) {
			return new ArrayList<List<Integer>>();
		}
		Arrays.sort(nums);
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] > 0) {
				break;
			}
			int target = 0;
			target = target - nums[i];
			for (int j = i + 1; j < nums.length - 1; j++) {
				target = target - nums[j];
				for (int k = j + 1; k < nums.length; k++) {
					if (target == nums[k]) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[k]);
						set.add(list);
						continue;
					}
				}
				target = target + nums[j];
			}
		}
		return new ArrayList<List<Integer>>(set);
	}

	@Test
	public void m1() {
		int[] nums = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> lists = threeSum(nums);
		System.out.println(lists);

	}
}
