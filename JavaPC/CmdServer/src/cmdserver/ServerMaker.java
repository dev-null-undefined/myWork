/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Martin
 */
public class ServerMaker {

    public static void main(String[] args) {
        int port = 5454;
        ArrayList<Thread> servers = new ArrayList<>();
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Running");
            while (true) {
                Socket clientSocket = socket.accept();
                System.out.println("Getting conction: "+clientSocket.getInetAddress());
                PrintWriter clientSocketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader clientSocketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Making new server");
                Thread t = new CmdServer(clientSocketWriter, clientSocketReader, socket);
                System.out.println("Done");
                servers.add(t);
                t.start();
                System.out.println("Server is runing");
                clientSocketWriter.println("RUNNING");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
