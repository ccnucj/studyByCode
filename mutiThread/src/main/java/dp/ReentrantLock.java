package dp;

/**
 * 可重入锁的第一个demo
 *
 * @author: chenjie
 * @date: 2018/1/23
 */
public class ReentrantLock implements Runnable{
	public java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
	public int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 10000; j++) {
			lock.lock();
			try {
				i++;
			} finally {
				lock.unlock();
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock reentrantLock = new ReentrantLock();
		Thread t1 = new Thread(reentrantLock);
		Thread t2 = new Thread(reentrantLock);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(reentrantLock.i);

	}
}
