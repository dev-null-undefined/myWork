import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

public class Troll {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot r=new Robot();
		Random rng=new Random();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		while(true) {

			r.mouseMove(rng.nextInt((int) width),rng.nextInt((int) height));
			Thread.sleep(100);
		}																						
	}

	/**
	 * Create the application.
	 */
	

}
