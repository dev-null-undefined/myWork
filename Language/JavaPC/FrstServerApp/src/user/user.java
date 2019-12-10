/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author Martin
 */
public class user {

    public static void main(String[] args) {

        String serverIp = "127.0.0.1";
        int serverPort = 5056;
        String coChci;

        try {
            Scanner sc = new Scanner(System.in);
            Socket socket = new Socket(serverIp, serverPort);
            ObjectOutputStream socketWriter = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream socketReader = new ObjectInputStream(socket.getInputStream());
            System.out.print((String) socketReader.readObject()); 
            socketWriter.writeObject("1");
            System.out.print((String) socketReader.readObject()); 
            JFileChooser f=new JFileChooser();
            f.showOpenDialog(null);
            socketWriter.writeObject(ImageIO.read(f.getSelectedFile()));
            /*while (true) {
                System.out.print((String) socketReader.readObject());                
                coChci = sc.nextLine();
                System.out.println(coChci);
                
                
                socketWriter.writeObject(coChci); 
                
                System.out.println("Server: " +(String) socketReader.readObject());
                
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
