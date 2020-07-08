/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class MyReader extends Thread implements Closeable {

    final BufferedReader clientSocketReader;
    boolean run = true;

    public MyReader(InputStream dis) {
        InputStreamReader e;
        e = new InputStreamReader(dis);
        clientSocketReader = new BufferedReader(e);
    }

    @Override
    public void run() {

        while (run) {
            try {
                System.out.println("Server says: "+clientSocketReader.readLine());
            } catch (IOException ex) {
            }
        }
    }
    public void waitForAprove(){
        try {
            System.out.println("Server says: "+clientSocketReader.readLine());
        } catch (IOException ex) {
        }
    }
    @Override
    public void close() throws IOException {
        run = false;
        clientSocketReader.close();
    }

}
