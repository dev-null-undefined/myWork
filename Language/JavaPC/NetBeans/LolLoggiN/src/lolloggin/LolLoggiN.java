/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolloggin;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;

/**
 *
 * @author marti
 */
public class LolLoggiN {

    /**
     * @param args the command line arguments
     */
    public static void pressKeys(char[] keys) {
        try {
            Robot r = new Robot();
            Random ra=new Random();
            for (char key : keys) {
                KeyStroke ks = KeyStroke.getKeyStroke(key, 0);
                r.keyPress(ks.getKeyCode());
                Thread.sleep(ra.nextInt(10)+10);
                r.keyRelease(ks.getKeyCode());
            }
        } catch (AWTException ex) {
            Logger.getLogger(LolLoggiN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LolLoggiN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();     //getting Runtime object

        try {
            runtime.exec("C:\\Riot Games\\League of Legends\\LeagueClient.exe");        //opens new notepad instance
            Thread.sleep(30000);
            pressKeys("1kokos5454".toCharArray());
            //OR runtime.exec("notepad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
