/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invisiblefolder;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marti
 */
public class InvisibleFolder {

    private static String password = "heslo1234";
    private static String folderName="folder";

    /**
     * @param args the command line arguments
     */
    public static void lock() {
        try {
            // TODO code application logic here
            Runtime.getRuntime().exec("attrib +H +s lockedFodler");
        } catch (IOException ex) {
            Logger.getLogger(InvisibleFolder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void unlock() {
        try {
            // TODO code application logic here
            Runtime.getRuntime().exec("attrib -H -s lockedFodler");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(InvisibleFolder.class.getName()).log(Level.SEVERE, null, ex);
            }
            new File("lockedFodler").renameTo(new File(folderName));
        } catch (IOException ex) {
            Logger.getLogger(InvisibleFolder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Console console = System.console();
        Scanner sc = new Scanner(System.in);
        File folder = new File(folderName);
        System.out.println(folder.exists());
        if (folder.exists()) {
            System.out.println("Do you want to lock folder [Y/N]?");
            if (sc.nextLine().equalsIgnoreCase("Y")) {
                folder.renameTo(new File("lockedFodler"));
                lock();
            }
        } else {
            if (new File("lockedFodler").exists()) {
                if (console == null) {
                    System.out.print("Input your password:");
                    if (sc.nextLine().equals(password)) {
                        unlock();
                    }
                } else {
                    char[] passwordRead = console.readPassword("Input your password:");
                    if (new String(passwordRead).equals(password)) {
                        unlock();
                    }
                }
            } else {
                folder.mkdir();
            }
        }
        System.exit(0);

    }

}
