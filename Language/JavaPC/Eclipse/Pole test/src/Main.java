import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Kolik mest budete zadavat ?");
		int x = in.nextInt();
		String nazvy[] = new String[x];
		int pocet[] = new int[x];
		String jmeno;
		int oby = 0;

		for (int y = 0; y < x; y++) {

			System.out.println("Nazev mesta: ");
			jmeno = in.nextLine();
			Mesto ms = new Mesto(jmeno, oby);
			ms.setNazev(jmeno);
			in.nextLine();
			nazvy[y] = jmeno;
			System.out.println("Pocet Obyvatel: ");
			ms.setPocet(in.nextInt());
			pocet[y] = ms.getPocet();

			in.nextLine();
		}
		for (int z = 0; z < x; z++) {
			System.out.println(nazvy[z]);
			System.out.println(pocet[z]);
		}

	}

}