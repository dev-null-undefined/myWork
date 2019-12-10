/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
/*
import java.text.*; 
import java.util.*; */
import java.net.*;

/**
 *
 * @author Martin
 */
public class serverConect {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056); 

        // running infinite loop for getting 
        // client request 
        int count=1;
        boolean run=true;
        while (run) {
            Socket s = null;

            try {
                s = ss.accept();
                System.out.println("A new client is connected : " + s);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                System.out.print("Making new server for user : "+s+" ......       ");
                Thread t = new server(s, dis, dos,count);
                count++;
                System.out.println("...DONE.");
                t.start();
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}
