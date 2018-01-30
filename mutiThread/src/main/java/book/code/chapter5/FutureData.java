package book.code.chapter5;

/**
 * Created by 13 on 2017/5/8.
 */
public class FutureData implements Data {

	protected RealData realData = null;
	protected boolean isReady = false;

	public synchronized void setRealData(RealData realData) {
		if (isReady) {
			return;
		}
		this.realData = realData;
		isReady = true;
		System.out.println("构造完成，通知取结果的线程，此时可以取结果了......");
		notifyAll();//RealData已经被注入，通知getResult
	}


	@Override
	public synchronized String getResult() {   //会等待RealData构造完成
		while (!isReady) {
			try {
				System.out.println("此时还没构造好，加入等待队列");
				wait();             //一直等待，直到RealData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return realData.result;         //由RealData实现
	}
}
