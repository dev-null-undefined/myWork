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
public class Main {
    public static void main(String[] args) {
        Zahradnictvi z=new Zahradnictvi();
        float vyska=(float) 155.2;
        z.addPrvek(new Rostlina("1", vyska , 100));
        z.addPrvek(new Rostlina("2", vyska , 150));
        z.addPrvek(new Rostlina("3", vyska , 125));
        z.addPrvek(new Strom(true, "Strom 1", vyska , 10000));
        z.addPrvek(new Strom(false, "Strom neopada", vyska , 4000));
        z.addPrvek(new Strom(true, "Strom 2", vyska , 10));
        System.out.println(z.fromTo(11, 150));
        System.out.println(z.fromTo(0, 9000));
        System.out.println(z);
        
    }
    
}
