/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class User {

    private static boolean b = true;

    public static void main(String[] args) {
        cicle();
    }

    public static void cicle() {

        String serverIp = "127.0.0.1";
        int serverPort = 65535;
        String coChci;

        try {
            Socket socket = new Socket(serverIp, serverPort);
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            
            while (b) {
                try {
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    Thread.sleep(10);
                    Point newP = MouseInfo.getPointerInfo().getLocation();
                    socketWriter.println(p.x-newP.x);
                    socketWriter.println(p.y-newP.y);
                } catch (InterruptedException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
