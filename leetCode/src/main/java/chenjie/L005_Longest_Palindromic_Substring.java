package chenjie;

public class L005_Longest_Palindromic_Substring {

	public String longestPalindrome(String s) {

		if (s == null || s.length() == 0) {
			return "";
		}

		int start = 0;
		int end = 0;

		for (int i = 0; i < s.length() - 1; i++) {

			int len1 = longest(s, i, i);
			int len2 = longest(s, i, i + 1);

			int len = Math.max(len1, len2);

			if (end - start < len) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}

		}

		return s.substring(start, end + 1);
	}

	private int longest(String s, int left, int right) {

		while (left >= 0 && right < s.length()
				&& s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}

		return right - left - 1;
	}


	/**
	 * 第二种解法，利用动态规划
	 */
	public String longestPalindrome2(String s) {
		int length = s.length();
		boolean[][] dp = new boolean[length][length];
		int d = 0;
		int start = 0,end = 0;
		int max = 0;
		for( ;d < length; d++ ){
			for(int i = 0; i< length-d;i++){
				int j = i+d;
				if(s.charAt(i) == s.charAt(j)){
					if(d==0 || d==1){
						dp[i][j] = true;
					} else {
						dp[i][j] =  dp[i+1][j-1];
					}
					if(dp[i][j]){
						if(d+1 > max){
							max = d+1;
							start = i;
							end = j;
						}
					}
				}
			}
		}
		return s.substring(start,end + 1);
	}
}
