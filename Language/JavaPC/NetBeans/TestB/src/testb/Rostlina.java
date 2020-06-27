/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testb;

/**
 *
 * @author marti
 */
public class Rostlina {
    protected String nazev;
    protected float vyska;
    protected long cena;

    @Override
    public String toString() {
        return "Rostlina{" + "nazev=" + nazev + ", vyska=" + vyska + ", cena=" + cena + '}';
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setVyska(float vyska) {
        this.vyska = vyska;
    }

    public void setCena(long cena) {
        this.cena = cena;
    }

    public Rostlina(String nazev, float vyska, long cena) {
        this.nazev = nazev;
        this.vyska = vyska;
        this.cena = cena;
    }

    public String getNazev() {
        return nazev;
    }

    public float getVyska() {
        return vyska;
    }

    public long getCena() {
        return cena;
    }
    
}
