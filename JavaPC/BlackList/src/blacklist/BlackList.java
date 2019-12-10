/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacklist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author marti
 */
public class BlackList {

    public static void writeBlacklist(TreeSet<String> macs, File f, File whites) {
        if (f.canRead() && whites.canRead()) {
            if (f.isFile() && whites.isFile()) {
                try (FileReader fr = new FileReader(f);
                        FileReader frwhites = new FileReader(whites);) {
                    String readed = "";
                    int i;
                    while ((i = fr.read()) != -1) {
                        readed += (char) i;
                    }
                    String readedWhite = "";
                    while ((i = frwhites.read()) != -1) {
                        readedWhite += (char) i;
                    }
                    String[] lines = readed.split(System.lineSeparator());
                    String[] linesWhite = readedWhite.split(System.lineSeparator());
                    for (String line : lines) {
                        macs.add(line);
                    }
                    for (String line : linesWhite) {
                        macs.remove(line);
                    }
                    System.out.println(macs);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                    for (String s : macs) {
                        writer.write(s + System.lineSeparator());
                    }
                    writer.close();
                } catch (Exception ex) {
                    System.out.print(ex.getMessage());
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File out = new File(args[2]);
        String filterWifi = args[1];
        String[] filterWifis=filterWifi.split(",");
        File f = new File(args[0]);
        File white = new File(args[3]);
        if (f.canRead()) {
            if (f.isFile()) {
                try (FileReader fr = new FileReader(f);) {
                    String readed = "";
                    int i;
                    while ((i = fr.read()) != -1) {
                        readed += (char) i;
                    }
                    String[] lines = readed.split(System.lineSeparator());
                    LinkedList<String> afterMac = new LinkedList<>();
                    boolean after = false;
                    for (int temp = 0; temp < lines.length; temp++) {
                        //System.out.println(lines[temp]);
                        if (after) {
                            afterMac.add(lines[temp]);
                        }
                        if (lines[temp].contains("Station MAC")) {
                            after = true;
                        }
                    }
                    TreeSet<String> macAdress = new TreeSet<>();
                    for (String line : afterMac) {
                        if (line.length() > 10) {
                            int index = line.indexOf(",");
                            String mac = line.substring(0, index);
                            String macWifi = line.substring(index + 1, line.length() - 1);
                            index = macWifi.indexOf(",");
                            macWifi = macWifi.substring(index + 1, macWifi.length());
                            index = macWifi.indexOf(",");
                            macWifi = macWifi.substring(index + 1, macWifi.length());
                            index = macWifi.indexOf(",");
                            macWifi = macWifi.substring(index + 1, macWifi.length());
                            index = macWifi.indexOf(",");
                            macWifi = macWifi.substring(index + 2, macWifi.length());
                            if (filterWifi.equalsIgnoreCase("ignore")) {
                                macAdress.add(mac);
                            }else{
                                for(String test:filterWifis){
                                    if(test.equalsIgnoreCase(macWifi)){
                                        macAdress.add(mac);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //System.out.println(macAdress);
                    BlackList.writeBlacklist(macAdress, out, white);
                } catch (Exception ex) {
                    System.out.print("error" + ex);
                }
            }
        }
    }
}
