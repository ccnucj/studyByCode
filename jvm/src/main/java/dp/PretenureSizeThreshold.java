package dp;

/**
 * 设置参数，使大对象直接放在老年代
 *
 * @author chenjie
 * @create 2017-10-17 15:13
 */
public class PretenureSizeThreshold {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		byte[] a;
		a = new byte[4 * _1MB];
		System.out.println("just stop app");
	}
}
