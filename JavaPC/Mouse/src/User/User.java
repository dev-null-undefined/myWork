/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Martin
 */
public class User {

    public static boolean notSanding = false;
    private static boolean b = true;
    private static JPanel mainPanel;
    public static PrintWriter socketWriter;
    public static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        mainPanel = new JPanel();
        cicle();
    }

    public static void cicle() {

        String serverIp = "127.0.0.1";
        int serverPort = 65535;

        try {
            Socket socket = new Socket(serverIp, serverPort);
            socketWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            frame.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    while (true) {
                        System.out.println("a");
                        if (User.notSanding) {
                            
                            User.socketWriter.println("");
                            User.socketWriter.println(e.getButton());
                            return;
                        }
                    }

                }
            });
           // frame.add(mainPanel);
            while (b) {
                try {
                    Point newP = MouseInfo.getPointerInfo().getLocation();
                    Thread.sleep(10);
                    notSanding = false;
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    socketWriter.println("COORD");
                    socketWriter.println(p.x - newP.x);
                    socketWriter.println(p.y - newP.y);
                    notSanding = true;
                } catch (InterruptedException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
