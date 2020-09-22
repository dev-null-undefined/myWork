
public class Firma implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5171843612320457746L;
	public String jmeno;
	public String adresa;
	public String DIC;
	public Firma(String jmeno,String addresa,String DIC) {
		this.jmeno=jmeno;
		this.adresa=addresa;
		this.DIC=DIC;
	}
	@Override
	public String toString() {
		return "Obchod: " + jmeno + "\nAdresa: " + adresa + "\nDIC: " + DIC;
	}
	
}
