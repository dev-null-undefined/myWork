
public class Zamestnanec implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4897982960298233978L;
	public String Jmeno;
	public int Id;

	public Zamestnanec(String jmeno, int id) {
		super();
		Jmeno = jmeno;
		Id = id;
	}

	@Override
	public String toString() {
		return "Zamestnanec: " + Jmeno + ", Id: " + Id;
	}
}