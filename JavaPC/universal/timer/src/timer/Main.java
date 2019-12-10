package timer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Main {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 212, 79);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(248, 11, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		Button button = new Button("STOP");
		button.setBounds(225, 161, 70, 22);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("PAUSE");
		button_1.setBounds(10, 229, 70, 22);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("RESUME");
		button_2.setEnabled(false);
		button_2.setBounds(10, 177, 70, 22);
		frame.getContentPane().add(button_2);
	}
}
