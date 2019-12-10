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
import java.util.ArrayList;
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

public class main {
	private static JButton button_A = new JButton("."), button_B = new JButton("."), button_C = new JButton("."),
			button_D = new JButton("."), button_E = new JButton("."), button_F=new JButton("."), button_G=new JButton(".");
	private static ArrayList<Kouzlo> listKouzel = new ArrayList<Kouzlo>();
	private static JTextArea text;
	private static Panel panel = new Panel();
	private static Button buttonExit = new Button("Vypnut\u00ED");
	private JFrame frmGui;
	static int maxHP = 10, hp = 10, dm = 8, ap = 0, zlataky = 20, level = 0, kdemax = 1, nextMagic = 11, kamJde,
			backStep = 5, backHP, backGolds, backXp;
	static double rs = 2;
	public static String polohy = "\nA)Les(Pouze zvířata(EASY))\nB)Jeskyně(těžší ale pořád easy)\nC)Džungle(to už je celkem fuška)\nD)PEKLO(ZÁKAZ VSTUPU)\nE)Město(NÁKUPY)",
			kamJde2;
	static String scene;/*
						 * sceny jsou 1)rozcesti 2)mesto 3)fight 4)kouzla 5)kouzelnik 6)obchod 7)hospoda
						 * 8)end
						 */
	static boolean kouzla = false, jsouKouzla = false, backJsouKouzla, hospoda;
	/*panel.removeAll();
	 *		panel.add(buttonExit);
	 *		panel.repaint();
	 *		panel.revalidate();
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

	public main() {
		initialize();
	}

	public static void Hospoda() {
		// TODO Auto-generated method stub

	}

	public static void Kouzelnik() {

	}

	public static void kouzlo() {
		String vypisKozel = "Toto jsou vaše nakoupená kouzla po použití zmizí!!\nVybírejte pomocí tlačítek: \n";
		for (int y = 0; y != listKouzel.size(); y++) {
			vypisKozel += (y + 1) + ")" + listKouzel.get(y).getJmeno() + "\n";
		}
		panel.removeAll();
		panel.add(buttonExit);
		text.setText(vypisKozel);
		switch (listKouzel.size()) {
		case 1:
			panel.add(button_A);
			panel.add(button_G);
			break;
		case 2:
			panel.add(button_A);
			panel.add(button_B);
			panel.add(button_G);
			break;
		case 3:
			panel.add(button_A);
			panel.add(button_B);
			panel.add(button_C);
			panel.add(button_G);
			break;
		case 4:
			panel.add(button_A);
			panel.add(button_B);
			panel.add(button_C);
			panel.add(button_D);
			panel.add(button_G);
			break;
		case 5:
			panel.add(button_A);
			panel.add(button_B);
			panel.add(button_C);
			panel.add(button_D);
			panel.add(button_E);
			panel.add(button_G);
			break;
		case 6:
			panel.add(button_A);
			panel.add(button_B);
			panel.add(button_C);
			panel.add(button_D);
			panel.add(button_E);
			panel.add(button_F);
			panel.add(button_G);
			break;
		}
		
		scene = "kouzla";
		setScene();

	}

	public static void setScene() {
		switch (scene) {
		case "rozcesti":
			panel.add(button_A);
			panel.add(button_B);
			panel.add(button_C);
			panel.add(button_D);
			panel.add(button_E);
			button_A.setText("A");
			button_B.setText("B");
			button_C.setText("C");
			button_D.setText("D");
			button_E.setText("E");
			text.setText(getAll()+"\nKam chcete jít: " + polohy);
			break;
		case "mesto":
			panel.removeAll();
			panel.add(buttonExit);
			panel.repaint();
			panel.revalidate();
			panel.add(button_A);
			panel.add(button_B);
			button_A.setText("1");
			button_B.setText("2");
			if (hospoda) {
				if (kouzla) {
					text.setText("Výtejte v městečku:\n 1)Obchod\n 2)Hospoda\n 3)Kouzelnik");
					panel.add(button_C);
					button_C.setText("3");
				} else {
					text.setText("Výtejte v městečku:\n 1)Obchod\n 2)Hospoda");
				}
			} else {
				if (kouzla) {
					text.setText("Výtejte v městečku:\n 1)Obchod\n 2)Hospoda(Zavřeno)\n 3)Kouzelnik");
					panel.add(button_C);
					button_C.setText("3");
					button_B.setEnabled(false);
				} else {
					text.setText("Výtejte v městečku:\n 1)Obchod\n 2)Hospoda(Zavřeno)");
					button_B.setEnabled(false);
				}
			}
			break;
		case "fight":
			panel.add(button_A);
			button_A.setText("Další souboj.");
			panel.add(button_B);
			button_B.setText("Jdi na rozcestí.");
			if (backStep > 0) {
				panel.add(button_C);
				button_C.setText("Jít spět(" + backStep + ") !!NEVRACÍ KOUZLA!!");
			}

			break;
		case "kouzla":
			button_A.setText("1");
			button_B.setText("2");
			button_C.setText("3");
			button_D.setText("4");
			button_E.setText("5");
			button_F.setText("6");
			button_G.setText("7");
			panel.repaint();
			panel.revalidate();
			break;
		case "kouzelnik":
			break;
		case "obchod":
			break;
		case "hospoda":
			break;
		case "end":
			break;
		}
	}

	public static void fight(String kde, int kdo) {
		scene = "fight";
		setScene();
		NPC les = new NPC(kdo);
		Random r = new Random();
		int x = les.getDM(), y = les.getHP(), hp2 = hp, dm2 = dm;
		String anima = les.getJmeno();

		hp = ((int) (hp - x / rs));
		if (nextMagic != 11) {
			switch (nextMagic) {
			case 1:
				dm2 += dm2;
				break;
			case 2:// kozlo(co dela)
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			}

		}
		if (hp < 1) {
			text.setText(getAll() + "\nVýtej v " + kde + " toto je tvuj nepřítel\n"
					+ anima.substring(0, anima.length() - 1) + "\n Jeho síla: " + x + "         Tvoje síla: " + dm2
					+ "\n                    X" + "\n Jeho životy: " + les.getHP() + "      Tvoje životy: " + hp2
					+ "\nSouboj dopadl: \n Jeho životy: " + (y -= dm2) + "      Tvoje životy: " + hp
					+ "\n!!!PROHRÁL JSI!!! ");
			int choose = JOptionPane.showConfirmDialog(null, "PROHRáL JSI CHCEŠ ODEJÍT?", "Jsi na odchodu?",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (choose == JOptionPane.YES_OPTION) {
				hp = backHP;
				zlataky = backGolds;
				level = backXp;
				backStep--;
				scene = "rozcesti";
				setScene();
			} else {
			}
		} else {
			if ((les.getHP() - dm2) > 0) {
				text.setText(getAll() + "\nVýtej v " + kde + " toto je tvuj nepřítel\n" + anima + "\n Jeho síla: " + x
						+ "         Tvoje síla: " + dm2 + "\n                    X" + "\n Jeho životy: " + les.getHP()
						+ "      Tvoje životy: " + hp2 + "\nSouboj dopadl: \n Jeho životy: " + (y -= dm2)
						+ "      Tvoje životy: " + hp + "\nNevidělal jsis ani korunu jelikož jsi ho neporazil. ");
			} else {
				int rand = r.nextInt(x) - x;
				text.setText(getAll() + "\nVýtej v " + kde + " toto je tvuj nepřítel\n"
						+ anima.substring(0, anima.length() - 1) + "\n Jeho síla: " + x + "         Tvoje síla: " + dm2
						+ "\n                    X" + "\n Jeho životy: " + les.getHP() + "      Tvoje životy: " + hp2
						+ "\nSouboj dopadl: \n Jeho životy: " + (y -= dm2) + "      Tvoje životy: " + hp
						+ "\n Vydělal jsis: " + (int) (x * 5 + 10 + rand) + "\nZískal jsi tím: "
						+ leveling(les.getDM(), les.getHP(), kdo) + "xp");
				if (anima.equalsIgnoreCase("Kouzelník ")) {
					kouzla = true;
				}
				zlataky += (int) ((x * 5 + 10 + rand) * kdo);
			}
		}

	}
		

	public static int leveling(int dm, int hp, int kde) {
		int plus = (((hp + dm) / 2) / ((level / 50) + 1)) * kde;
		level += plus;
		return plus;

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

	public static void Obchod() {
		text.setText("");
	}

	static boolean b = false;
	static String tecky = "";

	private void initialize() {
		/*
		 * frame setup
		 */
		frmGui = new JFrame();
		frmGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGui.setResizable(false);
		frmGui.setTitle("DRA\u010C\u00CD DOUP\u011A");
		frmGui.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmGui.setForeground(SystemColor.desktop);
		frmGui.setBounds(100, 100, 600, 372);
		frmGui.getContentPane().setLayout(null);
		frmGui.getContentPane().add(panel);
		panel.setBounds(396, 10, 188, 325);
		panel.setLayout(null);
		/*
		 * uvítací část
		 */
		JButton btnNerozumm = new JButton("Nerozum\u00EDm");
		btnNerozumm.setBounds(10, 52, 168, 30);
		panel.add(btnNerozumm);
		JButton button_Rozumim = new JButton("Rozum\u00EDm");
		button_Rozumim.setBounds(10, 11, 168, 30);
		panel.add(button_Rozumim);
		btnNerozumm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				text.setText("Tak si blbej??");
			}
		});
		button_Rozumim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				text.setText("Jdeme dál....\nNebo rovnou začneme :) \n" + getLevel() + "\n toto je tvuj level \n"
						+ zlataky
						+ "\ntoto jsou tve zlataky\nnejsi moc bohatý :)\nVždy se ti v rohu budou ukazovat \nživoty damege a magie \nBude to vypadat takto \nživoty\\damage\\štit\\kouzlo\nPři každý náštěvě města se ti regeneruje 1 život\nV městu mužeš udělat max 2 akce...");
				if (b) {
					panel.removeAll();
					panel.add(buttonExit);
					scene = "rozcesti";
					setScene();
				}
				b = true;
			}
		});
		/*
		 * vypnout button
		 */
		buttonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		buttonExit.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 17));
		buttonExit.setBackground(SystemColor.desktop);
		buttonExit.setForeground(SystemColor.text);
		buttonExit.setBounds(10, 293, 168, 22);
		panel.add(buttonExit);

		/*
		 * text setup
		 */

		text = new JTextArea();
		text.setBounds(10, 11, 355, 321);
		frmGui.getContentPane().add(text);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setText(
				"V této hřej jde o to že se musíš dostat \r\nco nejdále. Jsi kouzelník/bojovník a \r\npřed každým soubojem si můžeš \r\nnakoupit a tím zlepšit své schopnosti.\r\nZlatáky budeš vydělávat pomocí \r\nsoubojů.\r\n");
		text.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		text.setForeground(Color.WHITE);
		text.setBackground(Color.BLACK);
		/*
		 * buttony
		 */
		button_A.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "rozcesti":
					kamJde = 1;
					kamJde2 = "lesa";
					if (jsouKouzla && listKouzel.size() != 0) {
						kouzlo();
					} else {
						fight(kamJde2, kamJde);
					}
					break;
				case "mesto":
					Obchod();
					break;
				case "fight":
					fight(kamJde2, kamJde);
					break;
				case "kouzla":
					nextMagic = listKouzel.get(0).getID();
					listKouzel.remove(0);
					if (listKouzel.size() == 0) {
						jsouKouzla = false;
					}
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");
				}
			}
		});
		button_B.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "rozcesti":
					kamJde = 2;
					kamJde2 = "jeskině";
					if (jsouKouzla && listKouzel.size() != 0) {
						kouzlo();
					} else {
						fight(kamJde2, kamJde);
					}
					break;
				case "mesto":
					Hospoda();
					break;
				case "fight":
					scene = "rozcesti";
					setScene();
					break;
				case "kouzla":
					nextMagic = listKouzel.get(1).getID();
					listKouzel.remove(1);
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");

				}
			}

		});

		button_C.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "rozcesti":
					kamJde = 3;
					kamJde2 = "jungle";
					if (jsouKouzla && listKouzel.size() != 0) {
						kouzlo();
					} else {
						fight(kamJde2, kamJde);
					}
					break;
				case "mesto":
					Kouzelnik();
					break;
				case "fight":
					hp = backHP;
					zlataky = backGolds;
					level = backXp;
					backStep--;
					break;
				case "kouzla":
					nextMagic = listKouzel.get(2).getID();
					listKouzel.remove(2);
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");

				}
			}
		});
		button_D.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "rozcesti":
					kamJde = 4;
					kamJde2 = "PEKLU";
					if (jsouKouzla && listKouzel.size() != 0) {
						kouzlo();
					} else {
						fight(kamJde2, kamJde);
					}
					break;
				case "kouzla":
					nextMagic = listKouzel.get(3).getID();
					listKouzel.remove(3);
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");

				}
			}
		});
		button_E.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "rozcesti":
					scene = "mesto";
					setScene();
					break;
				case "kouzla":
					nextMagic = listKouzel.get(4).getID();
					listKouzel.remove(4);
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");

				}
			}
		});
		button_F.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "kouzla":
					nextMagic = listKouzel.get(5).getID();
					listKouzel.remove(5);
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");

				}
			}
		});
		button_G.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (scene) {
				case "kouzla":
					fight(kamJde2, kamJde);
					break;
				/*
				 * case "kouzelnik": break; case "obchod": break; case "end": break;
				 */
				default:
					text.setText("hmm");

				}
			}
		});

		/*
		 * odchod
		 */
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

	}

}
