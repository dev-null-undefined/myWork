
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marti
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static final Random r = new Random();

    public static void main(String[] args) {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(null);
        try (FileWriter fw = new FileWriter(fc.getSelectedFile().getAbsolutePath());) {
            long start, end;
            fw.write("Count,Time in milis,BubleSort,Array.sort,Stalin Sort" + System.lineSeparator());
            System.out.println(fc.getSelectedFile().getAbsolutePath());
            for (int i = 1; i < 1500; i++) {
                int count = i * 100;
                int[] pole = new int[count];
                int[] pole1 = new int[count];
                int[] pole2 = new int[count];
                for (int x = 0; x < pole.length; x++) {
                    pole[x] = r.nextInt(9999) + 1;
                    pole1[x] = r.nextInt(9999) + 1;
                    pole2[x] = r.nextInt(9999) + 1;
                }
                start = System.currentTimeMillis();
                Main.bubleSort(pole);
                end = System.currentTimeMillis();
                fw.write(i * 5 + "," + (end - start));

                start = System.currentTimeMillis();
                Arrays.sort(pole1);
                end = System.currentTimeMillis();
                fw.write("," + (end - start));

                start = System.currentTimeMillis();
                Main.StalinSort(pole2);
                end = System.currentTimeMillis();
                fw.write("," + (end - start) + System.lineSeparator());
                
                System.out.println(i*100);
                
                
                

            }
            /*
             fw.write(System.lineSeparator()+"Array.Sort");
             fw.write(System.lineSeparator());
             for (int i = 1; i < 10; i++) {
             int count = i * 1000;
             int[] pole = new int[count];
             for (int x = 0; x < pole.length; x++) {
             pole[x] = r.nextInt(100) + 1;
             }
                
             start = System.currentTimeMillis();
             Arrays.sort(pole);
             end = System.currentTimeMillis();
             fw.write(count + "," + (end-start)+System.lineSeparator());

             }
             fw.write(System.lineSeparator()+"Stalin sort");
             fw.write(System.lineSeparator());
             for (int i = 1; i < 10; i++) {
             int count = i * 1000;
             int[] pole = new int[count];
             for (int x = 0; x < pole.length; x++) {
             pole[x] = r.nextInt(100) + 1;
             }
             start = System.currentTimeMillis();
             Main.StalinSort(pole);
             end = System.currentTimeMillis();
             fw.write(count + "," + (end-start)+System.lineSeparator());

             }*/

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String poleToSting(int[] arr) {
        String returnString = "";
        for (int x = 0; x < arr.length; x++) {
            returnString += (x + 1) + ")" + arr[x] + "\n";
        }
        return returnString;
    }

    public static void bubleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements  
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
    }

    public static void StalinSort(int[] arr) {
        int[] sortedArr = new int[arr.length];
        int index = 0;
        for (int x = 1; x < arr.length; x++) {
            if (arr[x - 1] < arr[x]) {
                index++;
                sortedArr[index] = arr[x - 1];
            } else {

            }
        }
    }

}
