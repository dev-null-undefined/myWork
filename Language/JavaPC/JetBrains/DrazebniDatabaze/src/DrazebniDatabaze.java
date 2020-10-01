import java.util.*;

public class DrazebniDatabaze {
	private final TreeSet<Auto> auta;

	private Drazba aktualniDrazba;
	private final Queue<Drazba> frontaDrazeb;
	private final LinkedList<Drazba> ukonceneDrazby;

	public DrazebniDatabaze(TreeSet<Auto> auta) {
		super();
		if (auta == null) {
			throw new IllegalArgumentException("TreeSet auta nemuze byt NULL pouzijte prazdny konstruktor");
		}
		this.auta = auta;
		frontaDrazeb=new ArrayDeque<>();
		ukonceneDrazby=new LinkedList<>();
	}

	public DrazebniDatabaze() {
		super();
		this.auta = new TreeSet<>();
		frontaDrazeb=new ArrayDeque<>();
		ukonceneDrazby=new LinkedList<>();
	}
	public void UkonciDazbu(){
		ukonceneDrazby.add(aktualniDrazba);
		aktualniDrazba=frontaDrazeb.poll();
	}
	public boolean ZacniDrazbu(String nazev){
		Auto drazebniPolozka=this.Hledej(nazev);
		if(aktualniDrazba==null){
			aktualniDrazba = new Drazba(drazebniPolozka);
			return true;
		}
		frontaDrazeb.add(new Drazba(drazebniPolozka));
		return false;
	}
	public boolean ZacniDrazbu(Auto a) throws Exception {
		if(!this.auta.contains(a)){
			this.Pridej(a);
		}
		if (aktualniDrazba == null) {
			aktualniDrazba = new Drazba(a);
			return true;
		}
		frontaDrazeb.add(new Drazba(a));
		return false;
	}

	public Auto Hledej(String nazev) {

		for (Auto auto : auta) {
			if (auto.nazev().equals(nazev)) {
				return auto;
			}
		}
		throw new NoSuchElementException("Auto nenalezeno v databazi");
//		return null;
	}

	public void Pridej(Auto auto) throws Exception {
		if (!this.auta.add(auto)) {
			throw new Exception("Auto je uz zapsane v databazi");
		}
	}

	public String GetSerazene() {
		StringBuilder output = new StringBuilder();
		for (Auto a : auta) {
			output.append(a.toString()).append("\n");
		}
		return output.toString();
	}

	public TreeSet<Auto> getAuta() {
		return auta;
	}

	public Drazba getAktualniDrazba() {
		return aktualniDrazba;
	}

	public Queue<Drazba> getFrontaDrazeb() {
		return frontaDrazeb;
	}

	public LinkedList<Drazba> getUkonceneDrazby() {
		return ukonceneDrazby;
	}

}
