import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class DrazebniDatabaze {
	private Hashtable<String,Auto> auta;

	public DrazebniDatabaze(Hashtable<String,Auto> auta) {
		super();
		this.auta = auta;
	}

	public DrazebniDatabaze() {
		super();
		this.auta = new Hashtable<String,Auto>();
	}

	public void Pridej(Auto auto) throws Exception {
		if(this.auta.containsKey(auto.getNazev())) {
			throw new Exception("Auto je uz zapsane v databazi");
		}else {
			this.auta.put(auto.getNazev(), auto);
		}
	}

	public Auto Hledej(String nazev) {
		return auta.get(nazev);
	}

	public String GetSerazene() {
		String output = "";
		ArrayList<Auto> autaArray=new ArrayList<Auto>( auta.values());
//		Collections.sort(autaArray,new SortByPocetDveri());
		Collections.sort(autaArray);
//		Collections.sort(autaArray,new SortByCenna());
		for (Auto a :autaArray) {
			output += a.toString() + "\n";
		}
		return output;
	}

}
