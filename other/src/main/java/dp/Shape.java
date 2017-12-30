package dp;

import org.junit.Test;

import java.util.Comparator;

/**
 * 图形基类
 *
 * @author: chenjie
 * @date: 2017/12/30
 */
public class Shape {

	private double length;
	private double area;

	public Shape(double length, double area) {
		this.length = length;
		this.area = area;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public static Shape findMax(Shape[] arr, Comparator<Shape> cmp) {
		int maxIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		return arr[maxIndex];
	}

}
