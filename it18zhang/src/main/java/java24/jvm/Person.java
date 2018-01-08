package java24.jvm;

/**
 *
 */
public class Person {

	static {
		System.out.println("xxxx");
	}

	private String name;
	private int age;
	private Dog pet;

	public Person() {
		System.out.println("new Person()");
	}

	private Person(String name, int age) {
		System.out.println("2222");
	}

	private Person(String name, int[] age) {
		System.out.println("5555");
	}

	private Person(String name, Integer age) {
		System.out.println("3333");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Dog getPet() {
		return pet;
	}

	public void setPet(Dog pet) {
		this.pet = pet;
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

