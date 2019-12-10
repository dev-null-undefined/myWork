/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucty;

import java.util.Date;

/**
 *
 * @author Martin
 */


public class DebitUcet extends Ucet{
    private long debitLimit;
    public DebitUcet(String majitel, long stav,long debit) {
        super(majitel, stav);
        if(debit<0){
            throw new RuntimeException("Unsoported");
        }
        this.debitLimit=debit;
    }
    @Override
    public boolean upravStav(long money) {
        if ((stav + money) >= -debitLimit) {
            stav += money;
            pohyby.add(new Platba(new Date(), money));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DebitUcet{"+ "majitel=" + majitel + ", stav=" + stav + ", pohyby=" + pohyby+ "debitLimit=" + debitLimit + '}';
    }

}
