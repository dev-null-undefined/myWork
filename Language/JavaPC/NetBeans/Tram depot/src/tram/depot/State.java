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
public class State {
    public static final int NEW=1;
    public static final int GOOD=2;
    public static final int GOING=3;
    public static final int DEVASTETED=4;
    
    public static String intToString(int x){
        switch(x){
            case 1:
                return "NEW";
            case 2:
                return "GOOD";
            case 3:
                return "GOING";
            case 4:
                return "DEVASTETED";
        }
         return "UNKNOWN";
    }
    
}
