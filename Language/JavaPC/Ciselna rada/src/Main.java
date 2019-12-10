
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

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

    static boolean[] primes = new boolean[1000000000];
//set up the primesieve

    public static void fillSieve() {
        Arrays.fill(primes, true);        // assume all integers are prime.
        primes[0] = primes[1] = false;       // we know 0 and 1 are not prime.
        for (int i = 2; i < primes.length; i++) {
            //if the number is prime, 
            //then go through all its multiples and make their values false.
            if (primes[i]) {
                for (int j = 2; i * j < primes.length; j++) {
                    primes[i * j] = false;
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File f = new File("text.txt");
        FileWriter fw = new FileWriter(f);
        String lineSep = System.lineSeparator();
        fillSieve();
        HashMap<Integer, Integer> lastPos = new HashMap<>();
        int zeroCounter = 0;
        int pos = 0;
        int cur = 0;
        int nextNum = 0;
        do {
            if (lastPos.get(cur) == null) {
                nextNum = 0;
            } else {
                nextNum = pos - lastPos.get(cur) - 1;
            }
            lastPos.put(cur, pos );//--------------------------
            pos++;
            cur = nextNum;
            //System.out.print("," + nextNum);
            if (pos % 50 == 0) {
                System.out.println("");
            }
            /* if(cur==8){
             System.out.println("\n"+pos+",n");
             }*/
            if (nextNum == 0) {
                zeroCounter++;
            }
            if (pos % 1000 == 0) {
                Integer max = lastPos.keySet().stream().reduce((a, b) -> {
                    if (a > b) {
                        return a;
                    }
                    return b;
                }).get();
                int o;
                for (o = 0; lastPos.get(o) != null; o++) {
                }
                //System.out.println("Smallest missing:" + o);

                fw.append(pos + "," + max + "," + zeroCounter + "," + o + "," + getNumberOfPrime(lastPos.keySet()) + "," + getNumberOfOd(lastPos.keySet()) + "," + getNumberOfNotOd(lastPos.keySet()) + "," + getKeyByValue(lastPos, (lastPos.get(max) - 1)) + lineSep);

            }
        } while (pos != 100000000);
        fw.close();
        /*System.out.println(lastPos.toString());
         System.out.println("zeros:" + zeroCounter);
         System.out.println("max;" + max);
         System.out.println("Before max:" + getKeyByValue(lastPos, lastPos.get(max) - 1));
         System.out.println("Number of NOT Od:" + getNumberOfNotOd(lastPos.keySet()));
         System.out.println("Number of Od:" + getNumberOfOd(lastPos.keySet()));
         System.out.println("Number of primes:" + getNumberOfPrime(lastPos.keySet()));*/

    }

    public static Integer getNumberOfNotOd(Set<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> {
            if (b % 2 != 0) {
                return a + 1;
            }
            return a;
        });
    }

    public static Integer getNumberOfPrime(Set<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> {
            if (primes[b]) {
                return a + 1;
            }
            return a;
        });
    }

    public static Integer getNumberOfOd(Set<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> {
            if (b % 2 == 0) {
                return a + 1;
            }
            return a;
        });
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
