/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class server extends Thread {

    final int id;
    final ObjectInputStream dis;
    final ObjectOutputStream dos;
    final Socket s;
    private boolean run=true;
    private int next = -1;

    // Constructor 
    public server(Socket s, InputStream dis, OutputStream dos, int id) throws IOException {
        this.s = s;
        this.id = id;
        this.dis = new ObjectInputStream(dis);
        this.dos = new ObjectOutputStream(dos);
    }
    @Override
    public void run() {
        while (run) {
            String input;
            try {
                switch (next) {
                    case -1:
                        dos.writeObject("Nabidka:\n1)-JPG\nTvoje volba:");
                        
                        input = (String) dis.readObject();
                        System.out.println(id + ") input from server: " + input);
                        switch (input) {
                            case "1":
                                next = 1;
                                break;
                        }
                        
                        dos.writeObject(input);
                        //dos.flush();
                        
                        
                        break;
                    case 1:
                        BufferedImage img=(BufferedImage) dis.readObject();
                        System.out.println(id+")Get img");
                        break;
                        
                }
            } catch (IOException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
