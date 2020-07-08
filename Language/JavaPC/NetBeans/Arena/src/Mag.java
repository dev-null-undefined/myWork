/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Mag extends Postava {

    @Override
    public double getUtok() {
        return getInteligence() * 3;
    }

    @Override
    public double getObrana(Postava nepritel) {
        return nepritel.getVlastnost(this)+getOdolnost();
    }

    @Override
    public double getVlastnost(Postava postava) {
        return postava.getInteligence();
    }

}
