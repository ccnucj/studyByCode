package chenjie;

import org.junit.Test;

public class L058_Length_of_Last_Word {

	public int lengthOfLastWord(String s) {

		if (s == null || s.length() == 0) {
			return 0;
		}

		int len = 0;
		int i = s.length() - 1;

		while (i >= 0 && s.charAt(i) == ' ') {
			i--;
		}

		while (i >= 0 && s.charAt(i) != ' ') {
			len++;
			i--;
		}

		return len;
	}

	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		if(n==1){
			res[0][0] = 1;
			return res;
		}
		else {
			generateMatrix(res,n);
		}
		return res;

	}

	private void generateMatrix(int[][] res, int n) {
		int count = 0;
		int startX = 0, endX = n-1;
		int startY = 0, endY = n-1;
		while(startX <= endX && startY <= endY){
			for(int y = startY; y<= endY; y++){
				res[startX][y] = ++count;
			}
			for(int x = startX+1; x <= endX; x++){
				res[x][endY] = ++count;
			}
			if(startX == endX || startY == endY) {
				break;
			}
			for(int y = endY - 1; y >= startY; y--) {
				res[endX][y] = ++count;
			}
			for(int x = endX - 1; x >= startX+1; x--) {
				res[x][startY] = ++count;
			}
			startX++;
			startY++;
			endX--;
			endY--;
		}
	}

	@Test
	public void m1() {
		int[][] ints = generateMatrix(2);
		System.out.println(".....");
	}

}
