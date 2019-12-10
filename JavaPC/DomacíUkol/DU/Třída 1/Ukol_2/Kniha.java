package Ukol_2;

public class Kniha {
	private String autor;
	private String kniha;
	private int cena=200;

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getKniha() {
		return kniha;
	}

	public void setKniha(String kniha) {
		this.kniha = kniha;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Kniha(String autor, String kniha, int cena) {
		this.autor = autor;
		this.kniha = kniha;
		this.cena = cena;
	}
	public Kniha(String autor, String kniha) {
		this.autor = autor;
		this.kniha = kniha;
	}

	
}
