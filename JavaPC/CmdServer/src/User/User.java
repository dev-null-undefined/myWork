/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class User {

    public static void main(String[] args) {
        String serverIp = "192.168.0.5";
        int serverPort = 5554;

        try (Socket socket = new Socket(serverIp, serverPort);
                PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
                MyReader reader = new MyReader(socket.getInputStream());
                Scanner sc = new Scanner(System.in);) {

            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());

            socketWriter.println(sb.toString());

            System.out.println("Waiting for aprove.");
            reader.waitForAprove();
            reader.start();
            System.out.println("DONE");

            while (true) {
                System.out.print("Send: ");
                socketWriter.println(sc.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
