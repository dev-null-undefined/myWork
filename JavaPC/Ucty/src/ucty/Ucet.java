/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucty;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Martin
 */
public class Ucet {

    protected String majitel;
    protected long stav;
    protected LinkedList<Platba> pohyby = new LinkedList<>();

    public Ucet(String majitel, long stav) {
        this.majitel = majitel;
        this.stav = stav;
    }

    public String getMajitel() {
        return majitel;
    }

    public void setMajitel(String majitel) {
        this.majitel = majitel;
    }

    public long getStav() {
        return stav;
    }

    public LinkedList<Platba> getPohyby() {
        return pohyby;
    }

    public boolean upravStav(long money) {
        if ((stav + money) >= 0) {
            stav += money;
            pohyby.add(new Platba(new Date(), money));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ucet{" + "majitel=" + majitel + ", stav=" + stav + ", pohyby=" + pohyby + '}';
    }

}


