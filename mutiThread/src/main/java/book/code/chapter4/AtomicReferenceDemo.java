package book.code.chapter4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by 13 on 2017/5/6.
 * 为卡中余额小于20元的用户充值20元
 * 此列子可能导致为用户充值多次，要解决此问题，需要用带时间戳的引用 AtomicStampedReference
 */
public class AtomicReferenceDemo {

	//模拟多个线程同时更新后台数据库，为用户充值
	public static void main(String args[]) {
		final AtomicReference<Integer> money = new AtomicReference<Integer>();
		money.set(19);

		for (int i = 0; i < 100; i++) {
			new Thread() {
				public void run() {
					while (true) {
						Integer m = money.get();
						if (m < 20) {
							if (money.compareAndSet(m, m + 20)) {
								System.out.println("余额小于20元，充值成功，余额：" + money.get() + "元");
								break;
							}
						} else {
							System.out.println("余额大于20元，无需充值");
							break;
						}
					}
				}
			}.start();
		}

		//用户线程，模拟消费行为
		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {

					while (true) {
						Integer m = money.get();
						if (m > 10) {
							System.out.println("大于10元");
							if (money.compareAndSet(m, m - 10)) {
								System.out.println("成功消费10元，余额" + money.get());
								break;
							}
						} else {
							System.out.println("没有足够的金额");
							break;
						}
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
