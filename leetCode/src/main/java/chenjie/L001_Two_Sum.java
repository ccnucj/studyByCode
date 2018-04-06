package chenjie;

import java.util.HashMap;
import java.util.Map;

public class L001_Two_Sum {

	public int[] twoSum(int[] nums, int target) {

		if (nums == null || nums.length <= 1) {
			return new int[2];
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		// key = target - nums[i], just one solution
		for (int i = 0; i < nums.length; i++) {
			map.put(target - nums[i], i);       //期望有这些值
		}

		for (int i = 0; i < nums.length; i++) {
			Integer v = map.get(nums[i]);

			// can't use itself
			if (v != null && v != i) {         //有这些值，然后不是重复值相加
				return new int[] { i, v};
			}
		}

		return null;
	}
}
