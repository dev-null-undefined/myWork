/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucty;

/**
 *
 * @author Martin
 */
public class Ucty {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ucet mujUcet=new DebitUcet("Martan", 10000, 1000);
        System.out.println(mujUcet.upravStav(10));
        System.out.println(mujUcet.getStav());
        System.out.println("--");
        System.out.println(mujUcet.upravStav(-10000));
        System.out.println(mujUcet.getStav());
        System.out.println("--");
        System.out.println(mujUcet.upravStav(-20));
        System.out.println(mujUcet.getStav());
        System.out.println("--");
        System.out.println(mujUcet.upravStav(-10000));
        System.out.println(mujUcet.getStav());
        System.out.println("--");
        System.out.println(mujUcet.upravStav(1000));
        System.out.println(mujUcet.getStav());
        System.out.println("--");
        System.out.println(mujUcet);
    }
    
}
