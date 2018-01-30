package book.code.chapter3;

import java.util.concurrent.*;

/**
 * 线程池拒绝策略自定义实现
 * Created by 13 on 2017/5/5.
 */
public class RejectThreadPoolDemo {
	public static void main(String args[]) throws InterruptedException {
		MyTask myTask = new MyTask();

		/**
		 *  ThreadPoolExecutor 构造参数的含义
		 *
		 */
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10), Executors.defaultThreadFactory()
				, new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println(r.toString() + " is discard");
			}
		});

		for (int i = 0; i < 100; i++) {
			executorService.submit(myTask);
			Thread.sleep(10);
		}
	}

	public static class MyTask implements Runnable {

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
