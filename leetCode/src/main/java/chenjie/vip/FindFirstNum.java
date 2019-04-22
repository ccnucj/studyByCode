package chenjie.vip;

import java.util.HashSet;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author: chenjie
 * @date: 2019/4/16
 */
public class FindFirstNum {

	public int firstMissingPositive(int[] nums) {

		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		for (int i = 1 ;  ; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}

	}
}
