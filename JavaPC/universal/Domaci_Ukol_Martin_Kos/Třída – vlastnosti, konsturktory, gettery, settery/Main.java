import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.print("Zadejte jmeno Draka: ");
		String jmeno = new Scanner(System.in).nextLine();
		System.out.print("Zadejte počet hlav: ");
		int pocet = new Scanner(System.in).nextInt();
		Drak jedna = new Drak(pocet, jmeno);
		System.out.println("První drak se jmenuje " + jedna.getJmeno() + " a má " + jedna.getHlavy() + " Hlavy");

		System.out.print("Zadejte jmeno Draka: ");
		jmeno = new Scanner(System.in).nextLine();
		Drak dva = new Drak(jmeno);

		System.out.println("Druhý drak se jmenuje " + dva.getJmeno() + " a má " + dva.getHlavy() + " Hlavy");
	}

}
