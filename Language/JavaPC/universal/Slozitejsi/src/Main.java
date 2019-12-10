import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Konstruktor postava = new Konstruktor("", 0, 0, 0);
		postava.setHp(2);
		Scanner sc = new Scanner(System.in);

		System.out.print("Zadej jméno postavy: ");
		postava.setJmeno(sc.nextLine());

		System.out.print("Zadej inteligenci postavy: ");
		postava.setInteligence(sc.nextInt());

		for (; postava.getInteligence() + postava.getSila() > 10;) {
			if (postava.b == false) {
				System.out.print("Zadej znova inteligenci postavy: ");
				postava.setInteligence(sc.nextInt());
			}
		}

		System.out.print("Zadej sílu postavy: ");
		postava.setSila(sc.nextInt());
		for (; postava.getInteligence() + postava.getSila() > 10;) {
			if (postava.b == false) {
				System.out.print("Zadej znova sílu postavy: ");
				postava.setSila(sc.nextInt());
			}
		}
		System.out.println();

		Konstruktor bandita = new Konstruktor("bandita Fred", 1, 2, 1);
		Konstruktor pavouk = new Konstruktor("pavouk Tonda", 1, 3, 2);
		Konstruktor skolnik = new Konstruktor("školník Pepa", 1, 6, 1);
		Konstruktor prisera = new Konstruktor("Pøíšera", 1, 5, 4);
		int win = 0;
		int hp = 2;
		for (int v = 0; postava.getHp() != 0 && win != 4;) {
			System.out.println("1.) Jít do lesa");
			System.out.println("2.) Jít do jeskynì");
			System.out.println("3.) Jít do školy");
			System.out.println("4.) Jít do Voidu");
			System.out.println("5.) Zobrazit aktuální statistiky své postavy");

			v = sc.nextInt();
			switch (v) {
			case 1: {
				if (bandita.getHp() != 0) {
					System.out.println("Narazil jsi na banditu. " + bandita.Stats());
					if (postava.getSila() + postava.getInteligence() > bandita.getSila() + bandita.getInteligence()) {
						System.out.println("Porazil jsi banditu");
						bandita.setHp(0);
						win++;
						System.out.println();

						break;
					} else {
						System.out.println("Porazil tì bandita");

						hp--;
						postava.setHp(hp);
						System.out.println();

						break;

					}
				} else {
					System.out.println("Bandita je už mrtvej, jdi jinam");
					System.out.println();

					break;

				}
			}
			case 2: {
				if (pavouk.getHp() != 0) {
					System.out.println("Narazil jsi na pavouka. " + pavouk.Stats());
					if (postava.getSila() + postava.getInteligence() > pavouk.getSila() + pavouk.getInteligence()) {
						System.out.println("Porazil jsi pavouka");
						pavouk.setHp(0);
						win++;
						System.out.println();
						break;
					} else {
						System.out.println("Porazil tì pavouk");

						hp--;
						postava.setHp(hp);
						System.out.println();

						break;
					}
				} else {
					System.out.println("Pavouk je už mrtvej, jdi jinam");
					System.out.println();
					break;
				}
			}
			case 3: {
				if (skolnik.getHp() != 0) {
					System.out.println("Narazil jsi na školníka. " + pavouk.Stats());
					if (postava.getSila() + postava.getInteligence() > skolnik.getSila() + skolnik.getInteligence()) {
						System.out.println("Porazil jsi školníka");
						skolnik.setHp(0);
						win++;
						System.out.println();
						break;
					} else {
						System.out.println("Porazil tì školník");

						hp--;
						postava.setHp(hp);
						System.out.println();

						break;
					}
				} else {
					System.out.println("Školník je už mrtvej, jdi jinam");
					System.out.println();
					break;
				}
			}
			case 4: {
				if (prisera.getHp() != 0) {
					System.out.println("Narazil jsi na pøíšeru. " + prisera.Stats());
					if (postava.getSila() + postava.getInteligence() > prisera.getSila() + prisera.getInteligence()) {
						System.out.println("Porazil jsi pøíšeru");
						prisera.setHp(0);
						win++;
						System.out.println();
						break;
					} else {
						System.out.println("Porazila tì pøíšera");

						hp--;
						postava.setHp(hp);
						System.out.println();

						break;
					}
				} else {
					System.out.println("Pøíšera je už mrtvá, jdi jinam");
					System.out.println();
					break;
				}
			}
			case 5: {
				System.out.println(postava.Stats());
				System.out.println();
				break;
			}
			}

		}
		if (win == 4) {
			System.out.println("!!!     Vyhrál jsi     !!!");

		} else {
			System.out.println("!!!     Umøel jsi     !!!");
		}

	}
}
