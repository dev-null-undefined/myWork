
public class Pobocka implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1002140948598093034L;
	public int id;
	public Firma firma;
	public Pobocka(int id, Firma firma) {
		super();
		this.id = id;
		this.firma = firma;
	}
	@Override
	public String toString() {
		return "Id pobocky: " + id + "\n" + firma.toString();
	}
	
	
}
