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
public class Platba {
    private Date date;
    private long move;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMove(long move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return "Platba{" + "date=" + date + ", move=" + move + '}';
    }

    public Date getDate() {
        return date;
    }

    public long getMove() {
        return move;
    }

    public Platba(Date date, long move) {
        this.date = date;
        this.move = move;
    }
}
