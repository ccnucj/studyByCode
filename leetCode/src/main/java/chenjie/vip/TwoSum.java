package chenjie.vip;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author: chenjie
 * @date: 2019/4/16
 */
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer> numCountMap = new HashMap<>();
		for (int num : nums) {
			numCountMap.put(num,numCountMap.containsKey(num) ? numCountMap.get(num) + 1 : 1);
		}
		int[] arr = new int[2];
		for (int i = 0 ; i < nums.length; i++) {
			int one = nums[i];
			int another = target - one;
			if (!numCountMap.containsKey(another))
				continue;
			else {
				if (one == another && numCountMap.get(one) == 1) {
					continue;
				} else {
					arr[0] = i;
				}
				for (int j = 0 ; j < nums.length ; j++) {
					if (j != arr[0] && another == nums[j]) {
						arr[1] = j;
						return arr;
					}
				}
			}
		}
		return arr;
	}
}
