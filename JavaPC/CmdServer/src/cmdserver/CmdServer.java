/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdserver;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class CmdServer extends Thread implements Closeable {

    private boolean run = true;
    private String macAdress;

    public CmdServer(PrintWriter clientSocketWriter, BufferedReader clientSocketReader, ServerSocket socket) {
        this.clientSocketWriter = clientSocketWriter;
        this.clientSocketReader = clientSocketReader;
        this.socket = socket;
        try {
            macAdress = clientSocketReader.readLine();
        } catch (IOException ex) {
            
        }
    }

    final PrintWriter clientSocketWriter;
    final BufferedReader clientSocketReader;
    final ServerSocket socket;

    @Override
    public void run() {
        while (run) {
            try {
                String input=clientSocketReader.readLine();
                System.out.println("PC "+macAdress+" Says: "+input);
                Process p=Runtime.getRuntime().exec("cmd /c "+input);
                //System.out.print("Doing what you wanted");
                BufferedReader pReadder=new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line="";
                while((line=pReadder.readLine())!=null){
                    clientSocketWriter.println(line);
                }
                clientSocketWriter.println("ERROR");
                pReadder=new BufferedReader(new InputStreamReader(p.getErrorStream()));
                line="";
                while((line=pReadder.readLine())!=null){
                    clientSocketWriter.println(line);
                }
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public void close() throws IOException {
        run = false;
        socket.close();
        clientSocketReader.close();
        clientSocketWriter.close();
    }

}
