import java.text.SimpleDateFormat;
import java.util.Date;
enum Pohon{
	Diesel,Benzin,LPG,Elektro;
};
public class Auto implements Comparable {
	private Date datumVyroby;
	private int pocetDveri;
	private float cenna;
	private String nazev;
	private Pohon pohonVozidla;
	
	public Auto(Date datumVyroby, int pocetDveri, float cenna, String model, Pohon pohonVozidla) throws Exception {
		super();
		this.setCenna(cenna);
		this.setPocetDveri(pocetDveri);
		this.setDatumVyroby(datumVyroby);
		this.setNazev(model);
		this.setPohonVozidla(pohonVozidla);
	}

	@Override
	public String toString() {
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		return "Auto datumVyroby=" + DateFor.format(datumVyroby) + ", pocetDveri=" + pocetDveri + ", cenna=" + cenna + ", nazev="
				+ nazev + ", pohonVozidla=" + pohonVozidla + ",";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auto other = (Auto) obj;
		if (nazev == null) {
			if (other.nazev != null)
				return false;
		} else if (!nazev.equals(other.nazev))
			return false;
		return true;
	}

	public Date getDatumVyroby() {
		return datumVyroby;
	}
	public void setDatumVyroby(Date datumVyroby) {
		this.datumVyroby = datumVyroby;
	}
	public int getPocetDveri() {
		return pocetDveri;
	}
	public void setPocetDveri(int pocetDveri) throws Exception {
		if(pocetDveri<0) {
			throw new Exception("Nelze mit pocet dveri < nebo = 0.");
		}else {
			this.pocetDveri = pocetDveri;
		}
	}
	public float getCenna() {
		return cenna;
	}
	public void setCenna(float cenna) throws Exception {
		if(cenna<0) {
			throw new Exception("Nelze mit zapornou cennu.");
		}else {
			this.cenna = cenna;
		}
	}
	public String getNazev() {
		return nazev;
	}
	public void setNazev(String model) {
		this.nazev = model;
	}
	public Pohon getPohonVozidla() {
		return pohonVozidla;
	}
	public void setPohonVozidla(Pohon pohonVozidla) {
		this.pohonVozidla = pohonVozidla;
	}

	@Override
	public int compareTo(Object arg0) {
		if(arg0 instanceof Auto) {
			Auto compare=(Auto) arg0;
			return -compare.getNazev().compareTo(this.getNazev());
		}
		return 0;
	}
	
}
