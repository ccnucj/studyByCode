package chenjie;

public class L055_Jump_Game {

	public boolean canJump(int[] nums) {

		if (nums == null || nums.length == 0) {
			return false;
		}

		int maxStep = 0;

		for (int i = 0; i < nums.length; i++) {

			if (maxStep >= nums.length - 1) {
				return true;
			}

			if (nums[i] == 0 && maxStep == i) {
				return false;
			}

			maxStep = Math.max(maxStep, nums[i] + i);
		}

		return true;
	}

	public boolean canJump2(int[] nums) {
		int currMaxStep = nums[0];  //当前能够跳跃的最大步数
		for (int i = 1; i < nums.length; ++i) {
			if (i > currMaxStep) return false;
			currMaxStep = Math.max(i + nums[i], currMaxStep);
		}
		return currMaxStep >= nums.length - 1;

	}

}
