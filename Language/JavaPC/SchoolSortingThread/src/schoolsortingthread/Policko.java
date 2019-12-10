/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsortingthread;

import java.util.Random;

/**
 *
 * @author marti
 */
public class Policko {
    private int[] pole;
    private static final Random rnd=new Random();
    public Policko(int n){
        pole=new int[n];
        for(int i=0;i<n;i++){
            pole[i]=rnd.nextInt(100);
        }
    }
    public int[] getPole(){
        return pole;
    }
    public int max(){
        int m=pole[0];
        for (int i=1;i<pole.length;i++){
            m=pole[i]<m?m:pole[i];
        }
        return m;
    }
     public int min(){
        int m=pole[0];
        for (int i=1;i<pole.length;i++){
            m=pole[i]>m?m:pole[i];
        }
        return m;
    }
    
}
