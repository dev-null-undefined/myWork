import java.util.ArrayList;
import java.util.Scanner;

public class main {
	public static boolean idTwo(double a) {
		if (a <= 9) {
			return true;
		}
		return false;
	}

	public static boolean isInteger(String in) {
		try {
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static ArrayList<znamka> predmet(boolean in, boolean vypis, ArrayList<znamka> predmet) {
		boolean repeatMatematika = in;
		boolean tabulka = vypis;
		String input;
		boolean isInteger;
		znamka Znamka;
		Scanner into = new Scanner(System.in);

		while (repeatMatematika == true) {
			System.out.print("Zadejte známku (pokud už nechcete zadejte no/NO) : ");
			input = into.next();
			isInteger = isInteger(input);
			if (isInteger) {
				if (Integer.valueOf(input) >= 1 && Integer.valueOf(input) <= 5) {
					predmet.add(Znamka = new znamka(Integer.valueOf(input)));
				} else {
					System.out.println("Znamka může být od 1-5.");
				}
			} else {
				if (predmet.isEmpty()) {
					System.out.println("Prosím zadejte alespoň jednu známku.");
				} else {
					if (input.equalsIgnoreCase("no")) {
						repeatMatematika = false;
					} else {
						System.out.println("Co to děláte? Co my to zadáváte?  (" + input + ")  Co to má být?");
					}

				}
			}
		}
		if (tabulka) {
			if (predmet.size() > 0) {
				int názvy = predmet.size();
				int pomocny = 1;
				while (názvy > 0) {
					System.out.format("%3d.", pomocny);
					pomocny = pomocny + 1;
					názvy = názvy - 1;
				}
				System.out.println(" Průměr");
				double celkem = 0;
				int pocetproprumer = predmet.size();
				int kolik1 = 0;
				while (pocetproprumer > 0) {
					celkem = celkem + Double.valueOf(predmet.get(kolik1).getZnamka());
					pocetproprumer = pocetproprumer - 1;
					kolik1 = kolik1 + 1;
				}

				double pruměr = Double.valueOf(celkem) / Double.valueOf(predmet.size());
				System.out.print("+");
				int pocetprotabulku1 = predmet.size();
				while (pocetprotabulku1 >= 1) {
					System.out.print("---+");
					pocetprotabulku1 = pocetprotabulku1 - 1;
				}
				System.out.print("----+");
				System.out.println("");
				int pocetprotabulku2 = predmet.size();
				int pocetstalej = predmet.size();
				System.out.print("|");
				int kolik = 0;
				while (pocetprotabulku2 > 0) {
					System.out.print(" ");
					System.out.print(predmet.get(kolik).getZnamka());
					System.out.print(" ");
					System.out.print("|");
					pocetprotabulku2 = pocetprotabulku2 - 1;
					kolik = kolik + 1;
				}
				System.out.format("%1.2f", pruměr);
				System.out.print("|");
				System.out.println("");
				System.out.print("+");
				int pocetprotabulku3 = predmet.size();
				while (pocetprotabulku3 >= 1) {
					System.out.print("---+");
					pocetprotabulku3 = pocetprotabulku3 - 1;
				}
				System.out.print("----+");
				System.out.println("");

			} else {
				System.out.println("Nejsou zde žádné známky z tohoto předmětu.");
				System.out.println(" ");
			}
		}
		return predmet;
	}

	public static void tabulka(ArrayList<znamka> AJ, ArrayList<znamka> matematika, ArrayList<znamka> CJ) {
		System.out.println("Matematika: ");
		predmet(false, true, matematika);
		System.out.println("CJ: ");
		predmet(false, true, CJ);
		System.out.println("AJ: ");
		predmet(false, true, AJ);
	}

	public static ArrayList<znamka> Zmena(ArrayList<znamka> predmet) {
		predmet = predmet(false, true, predmet);
		znamka Znamka;
		Scanner into = new Scanner(System.in);
		boolean isInt, repeat = true;
		if (predmet.isEmpty() == false) {
			while (repeat) {
				System.out.println("Kolikátou známku chcete změnit? Pokud už měnit nechete zadejte no.");
				String input = into.nextLine();
				if (isInt = isInteger(input)) {
					if (Integer.valueOf(input) > 0) {
						if (predmet.isEmpty() == false) {
							if (Integer.valueOf(input) <= predmet.size()) {
								System.out.println("Zadejte známku(od 1 do 5) pokud chcete smazat zadejte (delete): ");
								String input1 = into.nextLine();
								if (isInt = isInteger(input1)) {
									if (Integer.valueOf(input1) >= 1 && Integer.valueOf(input1) <= 5) {
										int znamka = Integer.valueOf(input1);
										predmet.get(Integer.valueOf(input) - 1).setZnamka(znamka);
										predmet(false, true, predmet);
									} else {
										System.out.println("Zadejte známku od 1 do 5!!");
									}
								} else {
									if (input1.equalsIgnoreCase("delete")) {
										predmet.remove(Integer.valueOf(input) - 1);
										predmet(false, true, predmet);
									} else {
										System.out.println(
												"Zadejte známku(od 1 do 5) pokud chcete smazat zadejte (delete): ");
									}
								}
							} else {
								System.out.println("Známka číslo " + input + " nebyla nalezena.");
							}
						} else {
							System.out.println("Nelze upravovat prázdný seznam.");
						}
					} else {
						if (predmet.isEmpty() == false) {
							System.out.println("Zadejte číslo od:1 do:" + predmet.size());
						} else {
							System.out.println("Nelze upravovat prázdný seznam.");
						}
					}

				} else {
					if (input.equalsIgnoreCase("no")) {
						repeat = false;
					} else {
						if (predmet.isEmpty() == false) {
							System.out.println("Zadejte číslo od:1 do:" + predmet.size());
						} else {
							System.out.println("Nelze upravovat prázdný seznam.");
						}
					}
				}
			}
		}
		return predmet;
	}

	public static void main(String[] args) {
		ArrayList<znamka> matematika = new ArrayList<znamka>();
		ArrayList<znamka> CJ = new ArrayList<znamka>();
		ArrayList<znamka> AJ = new ArrayList<znamka>();
		String input;
		boolean isInt;
		int intInput;
		boolean repeat = true;

		Scanner into = new Scanner(System.in);
		while (repeat) {
			System.out.println("Ahoj toto je dnesni nabidka: \n 1)pridat \n 2)upravit \n 3)vypsat");

			input = into.next();
			isInt = isInteger(input);
			if (isInt) {
				intInput = Integer.valueOf(input);
				switch (intInput) {
				case 1:
					System.out.println("\n\n\n\n\nCo chcete pridat:\n  a)Matematika\n  b)CJ\n  c)AJ ");
					input = into.next();
					switch (input) {
					case "a":
						matematika = predmet(true, false, matematika);
						break;
					case "b":
						CJ = predmet(true, false, CJ);
						break;
					case "c":
						AJ = predmet(true, false, AJ);
					default:
						System.out.println("Zadejte pismeno a,b nebo c.");
						break;
					}
					break;
				case 2:
					System.out.println("\n\n\n\n\nToto jde upravit:  \n  a)Matematika\n  b)CJ\n  c)AJ ");
					input = into.next();
					switch (input) {
					case "a":
						matematika = Zmena(matematika);
						break;
					case "b":
						CJ = Zmena(CJ);
						break;
					case "c":
						AJ = Zmena(AJ);
						break;
					default:
						System.out.println("Zadejte pismeno a,b nebo c.");
						break;
					}
					break;
				case 3:
					System.out.println("\n\n\n\n\nChces vypsat: \n  a)Matematika\n  b)CJ\n  c)AJ \n  d)vše");
					input = into.next();
					switch (input) {
					case "a":
						matematika = predmet(false, true, matematika);
						break;
					case "b":
						CJ = predmet(false, true, CJ);
						break;
					case "c":
						AJ = predmet(false, true, AJ);
						break;
					case "d":
						tabulka(AJ, matematika, CJ);
						break;
					default:
						System.out.println("Zadejte prosím a,b,c nebo d(malá písmena)");
					}
					break;
				default:
					System.out.println("Zadejte cislo od 1 do 3.");
					break;
				}
			}

		}
	}

}