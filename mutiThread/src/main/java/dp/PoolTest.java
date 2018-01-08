package dp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程数量过多时，有新请求到来时拒绝请求抛出的异常
 *
 * @author chenjie
 * @create 2017-09-21 17:04
 */
public class PoolTest extends Thread {

	private int id;


	public PoolTest(int id) {
		this.id = id;
	}

	public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 100000; i++) {
			PoolTest poolTest = new PoolTest(i);
			executorService.execute(poolTest);
		}


	}

	@Override
	public void run() {
		try {
			sleep(5000);
			System.out.println(id + "完成任务了。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
