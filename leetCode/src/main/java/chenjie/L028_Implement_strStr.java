package chenjie;

public class L028_Implement_strStr {

	public int strStr(String haystack, String needle) {

		if (haystack == null || needle == null
				|| haystack.length() < needle.length()) {
			return -1;
		}

		if (needle.length() == 0) {
			return 0;
		}

		for (int i = 0; i < haystack.length(); i++) {

			if (i + needle.length() > haystack.length()) {  //如果haystack子串长度已经小于needle长度
				return -1;
			}

			int m = i;
			for (int j = 0; j < needle.length(); j++) {
				if (needle.charAt(j) == haystack.charAt(m)) {
					if (j == needle.length() - 1) {
						return i;
					}
					m++;
				} else {
					break;
				}
			}
		}

		return -1;
	}

}
