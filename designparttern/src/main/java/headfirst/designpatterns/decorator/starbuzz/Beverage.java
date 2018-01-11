package headfirst.designpatterns.decorator.starbuzz;

/**
 * 饮料基类
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
