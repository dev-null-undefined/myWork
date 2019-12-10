import java.util.Scanner;

public class KrychlovaNadoba {
	private int delka;
	private int l;
	private int vobjem;

	public KrychlovaNadoba(int delka, int l, Scanner in) {
		super();
		this.delka = delka;
		this.l = l;
		vobjem = delka * delka * delka;
		this.vejde(in);
	}

	public void vejde(Scanner sc) {
		if (vobjem < l) {
			System.out.println("Nevejde se to tam o: " + ((vobjem - l) * -1));
			System.out.print("Zadejte stav n�doby(l): ");
			l = sc.nextInt();
			this.vejde(sc);
		}
		if (l < 0) {
			System.out.println("Stav n�doby(l) nesm� b�t -");
			System.out.print("Zadejte stav n�doby(l): ");
			l = sc.nextInt();
			this.vejde(sc);
		}
	}

	public void info() {
		System.out.println("Do N�doby se vejde " + this.vobjem + " a je v n� " + this.l);
	}

	public void plusL(int l, Scanner sc) {
		this.l += l;
		this.vejde(sc);
		this.info();
	}

}
