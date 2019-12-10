/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tram.depot;

/**
 *
 * @author Martin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Driver a=new Driver("Tomas", "Non", Experience.BEGINNER);
        Tram b=new Tram(513163, State.GOOD, 25, a);
        Tram c=new Tram(51363, 1, 254, a);
        TramDatabase park=new TramDatabase();
        System.out.println(park.addTram(b));
        System.out.println(park.addTram(c));
        System.out.println(park);
        System.out.println(park.getFreeTrams().toString());
        System.out.println();
        System.out.println(park.getTram(51363));
        
        
    }
    
}
