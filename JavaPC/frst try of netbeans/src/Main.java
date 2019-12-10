
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        LinkedList<String> st=new LinkedList<>();
       /* for(int x=0;x!=100000000;x++){
            st.add("HOJKZ");
        }*/
       String s="Emer159 Je Pica";
       System.out.print(s.chars().filter(x->x>64&&x<91).count());
        
      /*  for(int x=0;x!=100000000;x++){// 8 minutes 52 seconds arraylist
            System.out.print(st.get(x));
        }*/
    }
    
}
