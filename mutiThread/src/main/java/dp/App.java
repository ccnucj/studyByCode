package dp;

/**
 * Hello world!
 */
public class App extends Thread {
	static volatile int flag = 0;

	public static void main(String[] args) throws InterruptedException {

		App app1 = new App();
		App app2 = new App();
		app1.start();
		app2.start();
		app1.join();
		app2.join();
		System.out.println(flag);

	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			synchronized (App.class) {
				flag++;
			}
		}
	}
}
