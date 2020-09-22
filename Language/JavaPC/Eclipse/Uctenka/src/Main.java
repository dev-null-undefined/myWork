public class Main {

	public static void main(String[] args) {
		Firma firma = new Firma("Lidl", "Praha 7", "CZ 1213213");
		Pobocka pobocka = new Pobocka(1, firma);
		Uctenka uctenka = new Uctenka(001, pobocka);
		uctenka.setBIK("1209389-125412-124");
		uctenka.setFIK("1241555-125125-111");
		Zamestnanec zam = new Zamestnanec("Pavel Vaclav", 112);
		uctenka.setVystavil(zam);
		Polozka jablko = Polozka.createPolozka("Jablko nejlepsi kvality", 10.50f, Dan.A, 1011211);
		Polozka hruska = Polozka.createPolozka("Hruska nejlepsi kvality", 12.50f, Dan.B, 1215121);
		Polozka pomeranc = Polozka.createPolozka("Pomeranc nejlepsi kvality", 14.50f, Dan.C, 1215131);
		uctenka.pridejPolozku(jablko, 2);
		uctenka.pridejPolozku(hruska);
		uctenka.pridejPolozku(pomeranc);
		uctenka.pridejPolozku(1215131);
		uctenka.pridejPolozku(jablko);
		uctenka.ulozUctenku("C:\\Users\\marti\\Downloads\\file");
		uctenka = Uctenka.nactiUctenku("C:\\Users\\marti\\Downloads\\file");
		System.out.println(uctenka);
		System.out.println(Polozka.vsechnyPolozky.size());
		System.out.println(uctenka.pridejPolozku(1215131,4));
		System.out.println(uctenka);
	}

}