package dp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 会一组（5个）一组（5个）的打印输出
 *
 * @author: chenjie
 * @date: 2017/9/20
 */
public class SamaphoreTest extends Thread {

	Semaphore semaphore = new Semaphore(5);

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SamaphoreTest samaphoreTest = new SamaphoreTest();
		for (int i = 0; i < 20; i++) {
			exec.submit(samaphoreTest);
		}
	}

	@Override
	public void run() {

		try {
			semaphore.acquire();
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getId() + "done!!!");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
