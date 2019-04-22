package chenjie.vip;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: chenjie
 * @date: 2019/4/15
 */
public class WordsMatchWithStr {

	/**
	 * 30. 串联所有的单词子串
	 */
	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s.length() == 0 || words.length == 0) {
			return res;
		}
		//统计所有单词的长度，用来确定需要从右到左扫描的次数
		int wlength = words[0].length();
		int size = wlength * words.length;
		Map<String,Integer> map = new HashMap<>();
		for (String word : words) {
			map.put(word,map.containsKey(word) ? map.get(word) + 1 : 1);
		}

		for (int i = 0; i <= s.length() - size; i++) {
			Map<String,Integer> tempMap = new HashMap<>(map);
			String substring = s.substring(i, i + size);
			List<String> splitWords = new ArrayList<>();
			for (int j = 0 ; j < words.length; j++) {
				splitWords.add(substring.substring(j*wlength, j*wlength + wlength));
			}
			boolean success = true;
			for (String splitWord : splitWords) {
				if (!map.containsKey(splitWord)) {
					success = false;
					break;
				} else {
					Integer integer = tempMap.get(splitWord);
					if (integer == 0) {
						success = false;
						break;
					} else {
						tempMap.put(splitWord, integer -1);
					}
				}
			}
			if (success) {
				res.add(i);
			}
		}
		return res;
	}

	@Test
	public void m1() {
		String[] words = {"ab", "ba", "ba"};
		String str = "ababaab";
		List<Integer> res = findSubstring(str, words);
		System.out.println(res);
	}

}
