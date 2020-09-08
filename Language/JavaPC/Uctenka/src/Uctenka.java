import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class Uctenka implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3534539975633004432L;

	@Override
	public String toString() {
		String uctenka = "";
		uctenka += "Uctenka cislo: " + cisloUctenky + "\n";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String formattedDateTime = this.datumVydani.format(formatter);
		uctenka += "Datum: " + formattedDateTime + "\n";
		uctenka += pobocka + "\n";
		uctenka += vystavil + "\n";
		String polozkyToString = "";
		for (Polozka key : this.polozky.keySet()) {
			polozkyToString += this.polozky.get(key) + " | " + key.toString() + "\n";
		}
		uctenka += polozkyToString;
		uctenka += "DPH \n";
		for (Dan a : Dan.values()) {
			uctenka += a.toString() + " " + (int) (a.toFloat() * 100) + "%\n";
		}
		uctenka += "Cenna: " + this.getCenaCelkemSDani() + "\n";
		uctenka += "Z toho dan: " + this.getDanCelkem() + "\n";
		uctenka += "Rezim: " + (online ? "Online" : "Ofline") + "\n";
		uctenka += "FIK: " + FIK + "\n";
		uctenka += "BIK: " + BIK + "\n";
		return uctenka;
	}

	public int cisloUctenky;
	public LocalDateTime datumVydani;
	private Pobocka pobocka;
	public Hashtable<Polozka, Integer> polozky;
	private Zamestnanec vystavil;
	private boolean online;
	private String FIK, BIK;
	private float cenna = -1;
	private float dan = -1;

	public Uctenka(int cisloUctenky, Pobocka pobocka) {
		this.cisloUctenky = cisloUctenky;
		this.online = true;
		this.pobocka = pobocka;
		this.polozky = new Hashtable<Polozka, Integer>();
		this.cenna = 0.0f;
		this.dan = 0.0f;
		this.datumVydani = LocalDateTime.now();
	}

	public void pridejPolozku(Polozka pridej) {
		this.cenna = -1;
		this.dan -= 1;
		Integer value = this.polozky.get(pridej);
		if (value != null) {
			this.polozky.replace(pridej, value + 1);
		} else {
			this.polozky.put(pridej, 1);
		}
	}

	public void pridejPolozku(Polozka pridej, int pocet) {
		this.cenna = -1;
		this.dan -= 1;
		Integer value = this.polozky.get(pridej);
		if (value != null) {
			this.polozky.replace(pridej, value + pocet);
		} else {
			this.polozky.put(pridej, pocet);
		}
	}

	/**
	 * Jesli jiz byla polozka pridana do list vsech polozek Polozka.vsechnyPolozky
	 * najde polozku a prida pomoci kodu EAN.
	 * 
	 * @param EAN
	 * @return bool hodnotu zda polozka byla nalezena a pridana
	 */
	public boolean pridejPolozku(int EAN) {
		if (Polozka.vsechnyPolozky.get(EAN) != null) {
			this.pridejPolozku(Polozka.vsechnyPolozky.get(EAN));
			return true;
		}
		return false;
	}

	public boolean odeberPolozku(Polozka odeber) {
		this.cenna = -1;
		this.dan -= 1;
		return this.polozky.remove(odeber) != null;
	}

	public boolean odeberPolozku(Polozka odeber, int pocetKusu) {
		this.cenna = -1;
		this.dan -= 1;
		Integer value = this.polozky.get(odeber);
		if (value != null) {
			if (value > 1) {
				this.polozky.replace(odeber, value - 1);
			} else {
				this.polozky.remove(odeber);
			}
			return true;
		}
		return false;
	}

	private void countCennaCelkem() {
		if (this.cenna != -1) {
			return;
		}
		this.cenna = 0;
		this.dan = 0;
		for (Polozka key : this.polozky.keySet()) {
			this.cenna += key.getCenaSDani() * this.polozky.get(key);
			this.dan += key.getJenDan() * this.polozky.get(key);
		}
	}

	public float getCenaCelkemSDani() {
		this.countCennaCelkem();
		return this.cenna;
	}

	public float getDanCelkem() {
		this.countCennaCelkem();
		return this.dan;
	}

	public boolean ulozUctenku(String pathToFile) {
		try (FileOutputStream fileOut = new FileOutputStream(pathToFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
			out.writeObject(this);
			return true;
		} catch (IOException i) {
			i.printStackTrace();
		}
		return false;
	}

	public static Uctenka nactiUctenku(String pathToFile) {
		try (FileInputStream fileIn = new FileInputStream(pathToFile);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			Uctenka u = (Uctenka) in.readObject();
			in.close();
			fileIn.close();
			return u;
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Uctenka class not found");
			c.printStackTrace();
		}
		return null;
	}

	public LocalDateTime getDatumVydani() {
		return datumVydani;
	}

	public Pobocka getPobocka() {
		return pobocka;
	}

	public void setPobocka(Pobocka pobocka) {
		this.pobocka = pobocka;
	}

	public Zamestnanec getVystavil() {
		return vystavil;
	}

	public void setVystavil(Zamestnanec vystavil) {
		this.vystavil = vystavil;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getFIK() {
		return FIK;
	}

	public void setFIK(String fIK) {
		FIK = fIK;
	}

	public String getBIK() {
		return BIK;
	}

	public void setBIK(String bIK) {
		BIK = bIK;
	}

	public Hashtable<Polozka, Integer> getPolozky() {
		return polozky;
	}

}
