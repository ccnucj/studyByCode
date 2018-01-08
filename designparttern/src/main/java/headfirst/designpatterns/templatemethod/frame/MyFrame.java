package headfirst.designpatterns.templatemethod.frame;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 2L;

	public MyFrame(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(300,300);
		this.setVisible(true);
	}

	/**
	 * 这是一个钩子
	 * 在模板方法基类中，默认啥都不做
	 * @param graphics
	 */
	public void paint(Graphics graphics) {
		super.paint(graphics);
		System.out.println("目前为止啥都没做...");
		String msg = "I rule!!";
		graphics.drawString(msg, 100, 100);
	}

	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame("Head First Design Patterns");
	}
}
