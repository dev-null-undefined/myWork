/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoclicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class AutoClicker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            final Robot r = new Robot();
            new Scanner(System.in).nextLine();
            while (true) {
                r.mousePress( InputEvent.BUTTON1_MASK);
                Thread.sleep(5);
                r.mouseRelease(InputEvent.BUTTON1_MASK);
                Thread.sleep(25);
            }
        } catch (AWTException ex) {
            Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
