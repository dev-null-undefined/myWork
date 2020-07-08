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
public class Strom extends Rostlina {

    protected boolean opadaNaZimu;

    public Strom(boolean opadaNaZimu, String nazev, float vyska, long cena) {
        super(nazev, vyska, cena);
        this.opadaNaZimu = opadaNaZimu;
    }

    @Override
    public String toString() {
        return "Strom{" + "nazev=" + nazev + ", opadaNaZimu=" + opadaNaZimu + ", vyska=" + vyska + ", cena=" + cena + '}';
    }

    public void setOpadaNaZimu(boolean opadaNaZimu) {
        this.opadaNaZimu = opadaNaZimu;
    }

    public boolean isOpadaNaZimu() {
        return opadaNaZimu;
    }

}
