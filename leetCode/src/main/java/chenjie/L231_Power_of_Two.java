package chenjie;

public class L231_Power_of_Two {

	public boolean isPowerOfTwo(int n) {
		
		if (n <= 0) {
			return false;
		}
		return (n & (n - 1)) == 0;
	}
}
