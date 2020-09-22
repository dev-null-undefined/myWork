public record Uzivatel( String jmeno, int id) {
	public Uzivatel{
		if(jmeno==null){
			throw new IllegalArgumentException("Jmeno nemuze byt null");
		}
		if (jmeno.length() < 3) {
			throw new IllegalArgumentException("Jmeno musi byt delsi nez 2 znaky");
		}
	}
}
