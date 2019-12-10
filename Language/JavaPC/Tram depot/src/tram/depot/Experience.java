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
public class Experience {
    public static final int BEGINNER=1;
    public static final int ADVANCED=2;
    public static final int EXPERIENCED=3;
    public static String intToString(int x){
        switch(x){
            case 1:
                return "BEGINNER";
            case 2:
                return "ADVANCED";
            case 3:
                return "EXPERIENCED";
        }
         return "UNKNOWN";
    }
}
