import java.util.Hashtable;

public class Polozka implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7779894655452379879L;
	public static final Hashtable<Integer,Polozka> vsechnyPolozky=new Hashtable<Integer,Polozka>();

	@Override
	public String toString() {
		return jmeno + " | " + cenna + " | " + dan;
	}

	public String jmeno;
	private float cenna;
	public Dan dan;
	public int EAN;

	public static Polozka createPolozka(String jmeno, float cenna, Dan dan, int EAN) throws RuntimeException {
		if(Polozka.vsechnyPolozky.containsKey(EAN)) {
			throw new RuntimeException("Polozka se stejnym EAN je jiz v seznamu");
		}else {
			Polozka newPolozka=new Polozka(jmeno,cenna,dan,EAN);
			Polozka.vsechnyPolozky.put(EAN, newPolozka);
			return newPolozka;
		}
	}
	private Polozka(String jmeno, float cenna, Dan dan, int EAN) {
		super();
		this.jmeno = jmeno;
		this.cenna = cenna;
		this.dan = dan;
		this.EAN = EAN;
	}

	public void setCenna(int cenna) {
		if (cenna > 0) {
			this.cenna = cenna;
		}
	}

	public float getCenna() {
		return cenna;
	}

	public float getJenDan() {
		return this.cenna * this.dan.toFloat();
	}

	public float getCenaSDani() {
		return this.cenna * (1 + this.dan.toFloat());
	}
}
