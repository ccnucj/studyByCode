package book.code.chapter5;

/**
 * Created by 13 on 2017/5/8.
 */
public class Client {
	public Data request(final String queryStr) {
		final FutureData futureData = new FutureData();
		/**
		 * 一定要开一个线程返回
		 */
		new Thread() {
			public void run() {//RealData的构建很慢，所以在单独的线程中进行
				RealData realData = new RealData(queryStr);
				futureData.setRealData(realData);
			}
		}.start();
		return futureData;
	}
}
