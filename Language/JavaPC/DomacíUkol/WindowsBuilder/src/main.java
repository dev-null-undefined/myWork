import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.Panel;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageProducer;
import java.util.Random;
import java.awt.event.TextEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Button;
import java.awt.Canvas;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JSlider;
import javax.swing.JTextField;
/*
 * nedodělané ale ještě natom zapracuji
 */

public class main {
	static int level = 0;
	static int zlataky = 20;
	private JFrame frmGui;
	static int hp = 10;
	static int dm = 8;
	static int ap = 0;
	static double rs = 2;
	public static String polohy = "\nA)Les(Pouze zvířata(EASY))\nB)Jeskyně(těžší ale pořád easy)\nC)Džungle(to už je celkem fuška)\nD)PEKLO(ZÁKAZ VSTUPU)";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frmGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public main() {
		initialize();
	}

	public static String getLevel() {
		switch (level % 10) {
		case 1:
			return "lvl " + level / 10 + " +---------";
		case 2:
			return "lvl " + level / 10 + " ++--------";
		case 3:
			return "lvl " + level / 10 + " +++-------";
		case 4:
			return "lvl " + level / 10 + " ++++------";
		case 5:
			return "lvl " + level / 10 + " +++++-----";
		case 6:
			return "lvl " + level / 10 + " ++++++----";
		case 7:
			return "lvl " + level / 10 + " +++++++---";
		case 8:
			return "lvl " + level / 10 + " ++++++++--";
		case 9:
			return "lvl " + level / 10 + " +++++++++-";
		case 0:
			return "lvl " + level / 10 + " ----------";
		default:
			return "lvl " + level / 10 + " error";
		}
	}

	public static String getAll() {
		return zlataky + " " + " Zlataku\n" + getLevel() + "     " + hp + "\\" + dm + "\\" + rs + "\\" + ap;

	}

	/**
	 * Initialize the contents of the frame.
	 */

	static boolean b = false;
	static String tecky = "";

	private void initialize() {

		frmGui = new JFrame();
		frmGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGui.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(main.class.getResource("/emo/Smiling_Emoji_with_Eyes_Opened.png")));
		frmGui.setResizable(false);
		frmGui.setTitle("DRA\u010C\u00CD DOUP\u011A");
		frmGui.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmGui.setForeground(SystemColor.desktop);
		frmGui.setAlwaysOnTop(true);
		frmGui.setBounds(100, 100, 600, 372);
		frmGui.getContentPane().setLayout(null);

		Panel panel = new Panel();
		panel.setBounds(396, 10, 188, 325);
		frmGui.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnA = new JButton("Rozum\u00EDm");

		btnA.setBounds(10, 11, 168, 30);
		panel.add(btnA);
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setLineWrap(true);
		text.setText(
				"V této hřej jde o to že se musíš dostat \r\nco nejdále. Jsi kouzelník/bojovník a \r\npřed každým soubojem si můžeš \r\nnakoupit a tím zlepšit své schopnosti.\r\nZlatáky budeš vydělávat pomocí \r\nsoubojů.\r\n");
		text.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		text.setForeground(Color.WHITE);
		text.setBackground(Color.BLACK);
		text.setBounds(10, 10, 365, 325);
		frmGui.getContentPane().add(text);
		Button button = new Button("Vypnut\u00ED");

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		button.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 17));
		button.setBackground(SystemColor.desktop);
		button.setForeground(SystemColor.text);
		button.setBounds(10, 293, 168, 22);
		panel.add(button);

		JButton button_C = new JButton("A");
		button_C.setBounds(10, 93, 168, 30);
		panel.add(button_C);

		JButton button_AA = new JButton("A");
		button_AA.setBounds(10, 11, 168, 30);
		panel.add(button_AA);

		JButton button_D = new JButton("A");
		button_D.setBounds(10, 134, 168, 30);
		panel.add(button_D);

		JButton button_E = new JButton("A");
		button_E.setBounds(10, 175, 168, 30);
		panel.add(button_E);

		JButton button_F = new JButton("A");
		button_F.setBounds(10, 216, 168, 30);
		panel.add(button_F);

		JButton button_G = new JButton("A");
		button_G.setBounds(10, 257, 168, 30);
		panel.add(button_G);

		JButton button_A = new JButton("A");
		button_A.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String anima = NPC.anime();
				Random r = new Random();
				int x = Integer.valueOf(anima.substring(anima.length() - 1));
				anima = anima.substring(0, anima.length() - 1);
				int y = Integer.valueOf(anima.substring(anima.length() - 1));
				int hp2 = hp;
				int y2 = y;
				hp = (int) (hp - x / rs);
				if ((y2 - dm) > 0) {
					if (hp < 1) {
						text.setText(getAll() + "\nVýtej v lese toto je tvuj nepřítel\n"
								+ anima.substring(0, anima.length() - 1) + "\n Jeho síla: " + x
								+ "         Tvoje síla: " + dm + "\n                    X" + "\n Jeho životy: " + y2
								+ "      Tvoje životy: " + hp2 + "\nSouboj dopadl: \n Jeho životy: " + (y -= dm)
								+ "      Tvoje životy: " + hp + "\n!!!PROHRÁL JSI!!! ");
						panel.removeAll();
						panel.add(button);
						panel.repaint();
						panel.revalidate();
						int choose = JOptionPane.showConfirmDialog(null, "PROHRáL JSI CHCEŠ ODEJÍT?",
								"Jsi na odchodu?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (choose == JOptionPane.YES_OPTION) {
							System.exit(0);
						} else {
						}
					} else {
					text.setText(getAll() + "\nVýtej v lese toto je tvuj nepřítel\n"
							+ anima.substring(0, anima.length() - 1) + "\n Jeho síla: " + x
							+ "         Tvoje síla: " + dm + "\n                    X" + "\n Jeho životy: " + y2
							+ "      Tvoje životy: " + hp2 + "\nSouboj dopadl: \n Jeho životy: " + (y -= dm)
							+ "      Tvoje životy: " + hp + "\nNevidělal jsis ani korunu jelikož jsi ho neporazil. ");
					}
				} else {
					if (hp < 1) {
						text.setText(getAll() + "\nVýtej v lese toto je tvuj nepřítel\n"
								+ anima.substring(0, anima.length() - 1) + "\n Jeho síla: " + x
								+ "         Tvoje síla: " + dm + "\n                    X" + "\n Jeho životy: " + y2
								+ "      Tvoje životy: " + hp2 + "\nSouboj dopadl: \n Jeho životy: " + (y -= dm)
								+ "      Tvoje životy: " + hp + "\n!!!PROHRÁL JSI!!! ");
						panel.removeAll();
						panel.add(button);
						panel.repaint();
						panel.revalidate();
						int choose = JOptionPane.showConfirmDialog(null, "PROHRáL JSI CHCEŠ ODEJÍT?",
								"Jsi na odchodu?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (choose == JOptionPane.YES_OPTION) {
							System.exit(0);
						} else {
						}
					} else {
						int rand = r.nextInt(x) - x;
						text.setText(getAll() + "\nVýtej v lese toto je tvuj nepřítel\n"
								+ anima.substring(0, anima.length() - 1) + "\n Jeho síla: " + x
								+ "         Tvoje síla: " + dm + "\n                    X" + "\n Jeho životy: " + y2
								+ "      Tvoje životy: " + hp2 + "\nSouboj dopadl: \n Jeho životy: " + (y -= dm)
								+ "      Tvoje životy: " + hp + "\n Vydělal jsis: " + (int) (x * 5 + 10 + rand));
						zlataky += (int) (x * 5 + 10 + rand);
					}
				}
			}
		});
		frmGui.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new JOptionPane();
				JOptionPane.showConfirmDialog(null, "Chceš odejít z této nejlepší hry?", "Jsi na odchodu?",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				int chose = JOptionPane.YES_OPTION;
				if (chose == JOptionPane.YES_OPTION) {
					System.exit(0);

				} else {
				}
			}
		});
		button_A.setBounds(10, 11, 168, 30);
		panel.add(button_A);
		JButton btnNerozumm = new JButton("Nerozum\u00EDm");
		btnNerozumm.setBounds(10, 52, 168, 30);
		panel.add(btnNerozumm);

		JButton button_BB = new JButton("A");
		button_BB.setBounds(10, 52, 168, 30);
		panel.add(button_BB);

		JButton button_CC = new JButton("A");
		button_CC.setBounds(10, 93, 168, 30);
		panel.add(button_CC);

		JButton button_DD = new JButton("A");
		button_DD.setBounds(10, 134, 168, 30);
		panel.add(button_DD);

		JButton button_EE = new JButton("A");
		button_EE.setBounds(10, 175, 168, 30);
		panel.add(button_EE);

		JButton button_FF = new JButton("A");
		button_FF.setBounds(10, 216, 168, 30);
		panel.add(button_FF);

		JButton button_GG = new JButton("A");
		button_GG.setBounds(10, 257, 168, 30);
		panel.add(button_GG);

		panel.remove(button_C);
		panel.remove(button_D);
		panel.remove(button_E);
		panel.remove(button_F);
		panel.remove(button_G);
		panel.remove(button_A);
		panel.remove(button_AA);
		panel.remove(button_BB);
		panel.remove(button_CC);
		panel.remove(button_DD);
		panel.remove(button_EE);
		panel.remove(button_FF);
		panel.remove(button_GG);

		JButton button_B = new JButton("A");

		button_B.setBounds(10, 52, 168, 30);
		panel.add(button_B);
		button_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_B.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (int x = 0; x < 16; x++) {
					tecky += ".";
					text.setText(tecky);
					panel.repaint();
				}
				tecky = "";

			}
		});
		panel.remove(button_B);

		btnNerozumm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				text.setText("Tak si blbej??");
			}
		});
		btnA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				text.setText("Jdeme dál....\nNebo rovnou začneme :) \n" + getLevel() + "\n toto je tvuj level \n"
						+ zlataky
						+ "\ntoto jsou tve zlataky\nnejsi moc bohatý :)\nVždy se ti vrohu budou ukazovat \nživoty damege a magie \nBude to vypadat takto \nživoty\\damage\\štit\\kouzla");

				if (b) {
					panel.remove(btnA);
					panel.remove(btnNerozumm);
					text.setText(getAll() + polohy);
					panel.add(button_B);
					panel.add(button_A);
					panel.add(button_C);
					panel.add(button_D);
					panel.repaint();
					panel.revalidate();
				}
				b = true;
			}
		});
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
