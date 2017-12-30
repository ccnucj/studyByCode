package dp;

import java.util.Comparator;

/**
 * 函数对象实现函数接口
 *
 * @author: chenjie
 * @date: 2017/12/30
 */
public class CompareImpl implements Comparator<Shape> {


	public int compare(Shape o1, Shape o2) {
		double areaComp = o1.getArea() - o2.getArea();
		double lengtgComp = o1.getLength() - o2.getLength();
		if (areaComp > 0 && lengtgComp > 0) {
			return 1;
		}
		if (areaComp < 0 && lengtgComp < 0) {
			return -1;
		}
		return 0;

	}
}
