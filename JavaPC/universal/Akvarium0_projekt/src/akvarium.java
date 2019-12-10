
public class akvarium {
	private ryba[] seznamryb;
	private int teplo, pocet = 0;

	public akvarium(int teplo, int pocetryb) {
		this.teplo = teplo;
		seznamryb = new ryba[pocetryb];
	}

	public boolean addRyba(ryba s) {

		pocet++;
		if (((Interval) s.inte).isIn(teplo)) {
			seznamryb[pocet] = s;
			return true;
		} else {
			return false;
		}

	}

	public ryba[] getSeznamryb() {
		return seznamryb;
	}

	public int getTeplo() {
		return teplo;
	}

	public int getPocet() {
		return pocet;
	}

}
