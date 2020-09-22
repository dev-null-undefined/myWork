import java.util.Date;

public record Auto(String nazev, float cena, Pohon pohonVozidla,  int pocetDveri, Date datumVyroby) implements  Comparable<Auto>{
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Auto auto = (Auto) o;

		if (!nazev.equals(auto.nazev)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return nazev.hashCode();
	}

	public Auto{
		if (nazev == null) {
			throw new IllegalArgumentException("Nazev nemuze byt NULL");
		}
		if(nazev.length()<3){
			throw new IllegalArgumentException("Nazev nemuze byt mene nez 3 znaky");
		}
		if (pohonVozidla == null) {
			throw new IllegalArgumentException("Pohon Auta nemuze byt NULL");
		}
		if (cena < 0) {
			throw new IllegalArgumentException("Cena Auta nemuze bit zaporna");
		}
		if (pocetDveri < 0) {
			throw new IllegalArgumentException("Auto nemuze mit zaporny pocet dveri");
		}
		if(datumVyroby==null) {
			throw new IllegalArgumentException("Datum vyroby nemuze byt NULL");
		}
	}

	@Override
	public int compareTo(Auto o) {
		return o.nazev.compareTo(this.nazev);
	}
}
