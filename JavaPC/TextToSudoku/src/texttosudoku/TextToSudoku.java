/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttosudoku;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author marti
 */
public class TextToSudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        ArrayList<String> slova = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            String[] pices = input.split("0");//System.out.println(        )      
            slova.add(String.format("frstGame.setNumber(%s, %s, %s);\n", pices[0], pices[1], pices[2]));
            for (String s : slova) {
                System.out.print(s);
            }
        }
    }

}
