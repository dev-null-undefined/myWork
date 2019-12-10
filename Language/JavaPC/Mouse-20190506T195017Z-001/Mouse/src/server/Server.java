/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Martin
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int port = 5454;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            Socket clientSocket = serverSocket.accept();
            Robot r = new Robot();
            PrintWriter clientSocketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader clientSocketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                String load = clientSocketReader.readLine();
                if (load.equals("COORD")) {
                    int x = Integer.valueOf(clientSocketReader.readLine());
                    int y = Integer.valueOf(clientSocketReader.readLine());
                    r.mouseMove(p.x + x, p.y + y);
                } else {
                    int x = Integer.valueOf(clientSocketReader.readLine());
                    r.mousePress(x);
                    r.mouseRelease(x);
                    
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
