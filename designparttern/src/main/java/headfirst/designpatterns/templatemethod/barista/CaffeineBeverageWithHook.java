package headfirst.designpatterns.templatemethod.barista;

public abstract class CaffeineBeverageWithHook {

	/**
	 * 在基类中定义算法执行的基本步骤，声明为final，子类无法重载改变
	 */
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();
		}
	}
 
	abstract void brew();
 
	abstract void addCondiments();
 
	void boilWater() {
		System.out.println("Boiling water");
	}
 
	void pourInCup() {
		System.out.println("Pouring into cup");
	}

	/**
	 * 声明一个钩子，用于在子类中改变基类算法的细节
	 * @return
	 */
	boolean customerWantsCondiments() {
		return true;
	}
}
