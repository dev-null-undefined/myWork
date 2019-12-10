/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.PrintWriter;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class User {

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());

            while (lineScanner.hasNext()) {
                input = input + " " + lineScanner.next();
                if (input.contains(";")) {
                    return input;
                }
            }

            lineScanner.close();
        }
        scanner.close();
        return "";
    }

    public static void main(String[] args) {

        String username = "martin";
        String password = "Kos";
        String serverIp = "jdbc:mysql://jestrab.kolej.mff.cuni.cz:3306/test?useSSL=false";//192.168.0.170
        try (
                Class.forName("com.mysql.jdbc.Driver");
                Connection conector = DriverManager.getConnection(serverIp, username, password);
                Scanner sc = new Scanner(System.in);
                Statement state = (Statement) conector.createStatement();) {
            while (true) {
                try {
                    Random r = new Random();
                    System.out.print("Send: ");
                    String input = User.getInput();
                    // String input="use test;";
                    // for (int x = 0; x < 200000; x++) {
                    System.out.println("Sending: " + input);
                    state.execute(input);
                    ResultSet rs = state.getResultSet();
                    if (rs != null) {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        List allRows = new ArrayList();
                        while (rs.next()) {
                            int numberColumns = rsmd.getColumnCount();
                            System.out.print("Resoult:");
                            for (int i = 1; i <= numberColumns; i++) {
                                System.out.print(rs.getString(i).toString() + ",");
                            }
                            System.out.println("");
                        }
                    }
                    //input="insert into Telefony(Number,State) values("+(r.nextInt(888888888)+111111111)+","+(r.nextInt(300)+300)+");";
                    // }
                } catch (Throwable e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
