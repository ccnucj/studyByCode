package book.code.chapter5;

/**
 * Created by 13 on 2017/5/6.
 */
public class LazySingleton {
	private static LazySingleton instance = null;

	private LazySingleton() {
		System.out.println("LazySingleton is create");
	}

	public static synchronized LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
