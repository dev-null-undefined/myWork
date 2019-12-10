package Ukol_2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		Kniha jedna= new Kniha("Boženy Němcové","Babička",210);
		System.out.println("Kniha jedna je "+jedna.getKniha()+" napsal/a ji "+jedna.getAutor()+" stojí "+jedna.getCena());
		Kniha dva= new Kniha("Karla Čapka","RUR");
		System.out.println("Kniha dva je "+dva.getKniha()+" napsal/a ji "+dva.getAutor()+" stojí "+dva.getCena());
		System.out.println("Zmena ceny první na 250");
		jedna.setCena(250);
		System.out.println("Zmena jmena druhy ne \"Krakatit\"");
		dva.setKniha("Krakatit");
		System.out.println("Kniha jedna je "+jedna.getKniha()+" napsal/a ji "+jedna.getAutor()+" stojí "+jedna.getCena());
		System.out.println("Kniha dva je "+dva.getKniha()+" napsal/a ji "+dva.getAutor()+" stojí "+dva.getCena());
	}
}
