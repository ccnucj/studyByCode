package dp;

/**
 * ${DESCRIPTION}
 *
 * @author: chenjie
 * @date: 2018/1/16
 */
public class Child2 extends Child1 {

	public Child2(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Child2-" + super.getName();
	}
}
