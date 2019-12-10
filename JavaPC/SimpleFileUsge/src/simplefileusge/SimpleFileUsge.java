/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplefileusge;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 *
 * @author Martin
 */

public class SimpleFileUsge {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try(Stream<String> f = Files.lines(new File("txt.txt").toPath());){
            
           f.forEach(x->System.out.print(x+"--------"));
           
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
}
