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
        RidkaMatice<String> a=new RidkaMatice<>();
        System.out.println(a.add("HOVNO JSI", 1, 1));
        System.out.println(a.add("HOVNO JSI", 2, 1));
        System.out.println(a.add("HOVNO JSI", 2, 2));
        System.out.println(a.getAll("HOVNO JSI"));
        //System.out.println(a.remove("HOVNO JSI1"));
      /*  RidkaMatice<String> b=new RidkaMatice<>();
        System.out.println(b.add("HOVNO NEJSI1", 1, 1));
        System.out.println(b.add("HOVNO NEJSI2", 2, 1));
        System.out.println(b.add("HOVNO NEJSi3", 3, 2));
        System.out.println(b);
        a.addAll(b);
        System.out.print(a+"\n\n\n"+b);*/
        
    }
    
}
