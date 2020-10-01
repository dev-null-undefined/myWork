import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;

public class Main {

	protected static final int KeyCode = 0;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setFocusable(true);

		JPanel panel = new JPanel();
		JLabel up = new JLabel();
		JLabel down = new JLabel();
		JLabel right = new JLabel();
		JLabel left = new JLabel();


		panel.add(up);
		frame.add(panel);
		down.setText("down:0");
		left.setText("left: 0");
		up.setText("up: 0");
		right.setText("right: 0");
		frame.addKeyListener(new KeyListener() {

			int UpCount = 0;


			// This can be deleted
//			public void keyPressed1(KeyEvent e) {
//				switch (e.getKeyCode()) {
//					case KeyEvent.VK_UP:
//						up.setText("Up: " + Integer.toString(++UpCount));
//						break;
//				}
//			}
//			public void keyReleased1(KeyEvent e) {
//
//			}
//			public void keyTyped1(KeyEvent e) {
//
//			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						up.setText("Up: " + Integer.toString(++UpCount));
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}



}
