package book.code.chapter3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by 13 on 2017/5/5.
 */
public class CountTask extends RecursiveTask {    //继承RecursiveAction 没有返回结果
	private static final int THRESHOLD = 10000;

	private long start;
	private long end;

	public CountTask(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public static void main(String args[]) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();   //线程池
		CountTask task = new CountTask(0, 200000L);
		ForkJoinTask<Long> result = forkJoinPool.submit(task);  //将一个大的任务提交到线程池

		long res = 0;
		try {
			res = result.get();
			System.out.println("sum=" + res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Long compute() {             //必须自定义重写的方法
		long sum = 0;
		boolean canCompute = (end - start) < THRESHOLD;
		if (canCompute) {
			for (long i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			long step = (start + end) / 100;

			ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
			long pos = start;

			for (int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				if (lastOne > end) {
					lastOne = end;
				}
				CountTask subTask = new CountTask(pos, lastOne);
				pos += step + 1;
				subTasks.add(subTask);
				subTask.fork();          //fork表示可以分而治之
			}
			int count = 0;
			for (CountTask t : subTasks) {
				sum += (Long) t.join();     //获取分支线程的执行结果
				System.out.println(++count + "次累计的计算结果为" + sum);
			}
		}
		return sum;
	}
}
