package book.code.chapter3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器
 * Created by 13 on 2017/5/5.
 */
public class CountDownLatchDemo implements Runnable {
	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();

	public static void main(String args[]) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executorService.submit(demo);
		}
		//等待检查
		end.await();
		//倒计时完毕发射火箭
		System.out.println("Fire!");
		executorService.shutdown();
	}

	@Override
	public void run() {

		try {
			Thread.sleep(new Random().nextInt(3) * 1000);
			System.out.println("check complete");
			end.countDown();     //倒计时减一
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
