package dp;

/**
 * ${DESCRIPTION}
 *
 * @author: chenjie
 * @date: 2018/1/16
 */
public class Base1 {

	private String name;

	public Base1() {
	}

	public Base1(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Base1{" +
				"name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
