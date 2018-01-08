package proxyAndNIO.proxy.duration;

/**
 * 实现类
 */
public class MyServiceImpl implements MyService, YourService {
	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public float divide(int a, int b) {
		return (float) a / b;
	}

	public int shiftLeft(int a, int b) {
		return a << b;
	}

	public int shiftRight(int a, int b) {
		return a >> b;
	}

	public int singleAnd(int a, int b) {
		return a & b;
	}

	public boolean doubleAnd(int a, int b) {
		return false && true;
	}
}