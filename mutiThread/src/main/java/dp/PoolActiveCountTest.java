package dp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试当线程池数量达到一定值时决绝线程的提交
 *
 * @author chenjie
 * @create 2017-09-22 14:30
 */
public class PoolActiveCountTest {

	static volatile int count = 0;

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 1000000; i++) {
			ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
			if (executorService1.getActiveCount() >= 60) {
				System.out.println("线程池已达到满负荷，拒绝执行");
				break;
			} else {
				executorService1.execute(new Mythread(i));
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("已经完成的任务-->" + count);


	}

	static class Mythread extends Thread {

		private int id;

		Mythread(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (PoolActiveCountTest.class) {
				count++;
			}
			System.out.println(id + "完成了。。。");
		}
	}
}
