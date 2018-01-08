package proxyAndNIO;

/**
 *
 */
public class Person {

	private String name;

	private int age;

	private boolean married;

	public Person() {
	}

	public boolean isMarried() {
		return married;
	}


	public void setMarried(boolean married) {
		this.married = married;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void setName(String x, String y) {
		this.name = x + " : " + y;
	}
}

