package java21.screenbroadcast.student;

/**
 * StudentMain
 */
public class StudentMain {

	public static void main(String[] args) {
		StudentUI ui = new StudentUI();
		ReceiverThread t = new ReceiverThread(ui);
		t.start();
	}

}
