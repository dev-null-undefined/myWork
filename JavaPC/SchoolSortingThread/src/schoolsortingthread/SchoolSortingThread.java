/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsortingthread;

import java.sql.Array;
import java.util.Arrays;


/**
 *
 * @author marti
 */
public class SchoolSortingThread {

    /**
     * @param args the command line arguments
     */
    public static long startTime;
    public static void main(String[] args) {
        // TODO code application logic here
        Policko p=new Policko(100000000);
       Thread t=new Thread(){
            @Override
            public void run(){
                System.out.println(p.max());
        System.out.println(System.currentTimeMillis()-startTime);
            }
        };
        Thread t2=new Thread(){
            @Override
            public void run(){
                System.out.println(p.min());
        System.out.println(System.currentTimeMillis()-startTime);
            }
        };
        startTime = System.currentTimeMillis();
       /* t.start();
        t2.start();*/
        
        Arrays.sort(p.getPole());
         System.out.println(System.currentTimeMillis()-startTime);
        
    }
    
}
