
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Martin
 */
public class Euler {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(problem(0));
    }

    public static String problem(int x) {
        String resoult = "";
        switch (x) {
        case 0:// generating case and break;
            for (int y = 2; y < 100; y++) {
                resoult += "case " + y + ":\n" + "break;" + "\n";
            }
            break;
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        case 6:
            break;
        case 7:
            break;
        case 8:
            break;
        case 9:
            break;
        case 10:
            break;
        case 11:
            break;
        case 12:
            break;
        case 13:
            break;
        case 14:
            break;
        case 15:
            break;
        case 16:
            break;
        case 17:
            break;
        case 18:
            break;
        case 19:
            break;
        case 20:
            break;
        case 21:
            break;
        case 22:
            break;
        case 23:
            break;
        case 24:
            break;
        case 25:
            break;
        case 26:
            break;
        case 27:
            break;
        case 28:
            break;
        case 29:
            break;
        case 30:
            break;
        case 31:
            break;
        case 32:
            break;
        case 33:
            break;
        case 34:
            break;
        case 35:
            break;
        case 36:
            break;
        case 37:
            break;
        case 38:
            break;
        case 39:
            break;
        case 40:
            break;
        case 41:
            break;
        case 42:
            break;
        case 43:
            break;
        case 44:
            break;
        case 45:
            break;
        case 46:
            break;
        case 47:
            break;
        case 48:
            break;
        case 49:
            break;
        case 50:
            break;
        case 51:
            break;
        case 52:
            break;
        case 53:
            break;
        case 54:
            break;
        case 55:
            break;
        case 56:
            break;
        case 57:
            break;
        case 58:
            break;
        case 59:
            break;
        case 60:
            break;
        case 61:
            break;
        case 62:
            break;
        case 63:
            break;
        case 64:
            break;
        case 65:
            break;
        case 66:
            break;
        case 67:
            break;
        case 68:
            break;
        case 69:
            break;
        case 70:
            break;
        case 71:
            break;
        case 72:
            break;
        case 73:
            break;
        case 74:
            break;
        case 75:
            break;
        case 76:
            break;
        case 77:
            break;
        case 78:
            break;
        case 79:
            break;
        case 80:
            break;
        case 81:
            break;
        case 82:
            break;
        case 83:
            break;
        case 84:
            break;
        case 85:
            break;
        case 86:
            break;
        case 87:
            break;
        case 88:
            break;
        case 89:
            break;
        case 90:
            break;
        case 91:
            break;
        case 92:
            break;
        case 93:
            break;
        case 94:
            break;
        case 95:
            break;
        case 96:
            break;
        case 97:
            break;
        case 98:
            break;
        case 99:
            break;
        }
        return resoult;
    }

    public static int problemTwentyNine() {// 29
        TreeSet<String> setOfNum = new TreeSet<>();
        for (int x = 2; x <= 100; x++) {
            for (int y = 2; y <= 100; y++) {
                setOfNum.add(((new BigInteger(x + "")).pow(y)).toString());
            }
        }
        return setOfNum.size();
    }

    public static Integer[][] makeSpiral(int x) {
        Integer[][] spiral = new Integer[x][x];
        int way = 0, positionX = x - 1, positionY = 0;
        boolean doSet = true;
        for (int y = (x * x); y > 0;) {
            if (doSet) {
                if (spiral[positionY][positionX] != null) {
                    // we have to move back change directory and move
                    switch (way) {
                    case 0:
                        // System.out.print("case 0:" + way);
                        // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                        positionX++;
                        break;
                    case 1:
                        // System.out.print("case 1:" + way);
                        // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                        positionY--;
                        break;
                    case 2:
                        // System.out.print("case 2:" + way);
                        // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                        positionX--;
                        break;
                    case 3:
                        // System.out.print("case 3:" + way);
                        // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                        positionY++;
                        break;
                    }
                    doSet = false;
                    way++;
                } else {
                    // System.out.print("seting:" + y);
                    // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                    spiral[positionY][positionX] = y;

                    y--;
                }
            }
            switch (way) {
            case 0:
                // System.out.print("case 0:" + way);
                // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                if (positionX == 0) {
                    way++;
                    doSet = false;
                } else {
                    positionX--;
                    doSet = true;
                }
                break;
            case 1:
                // System.out.print("case 1:" + way);
                // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                if (positionY == x - 1) {
                    way++;
                    doSet = false;
                } else {
                    positionY++;
                    doSet = true;
                }
                break;
            case 2:
                // System.out.print("case 2:" + way);
                // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                if (positionX == x - 1) {
                    way++;
                    doSet = false;
                } else {
                    positionX++;
                    doSet = true;
                }
                break;
            case 3:
                // System.out.print("case 3:" + way);
                // System.out.println(" Position x:" + positionX + ",y:" + positionY);
                if (positionY == 0) {
                    way++;
                    doSet = false;
                } else {
                    positionY--;
                    doSet = true;
                }
                break;
            case 4:
                doSet = false;
                way = 0;
                break;
            }

        }
        return spiral;
    }

    public static String arrayToString(Object[][] a) {
        String ret = "";
        for (Object[] s : a) {
            for (Object l : s) {
                ret += (l == null ? "null" : l.toString()) + ",";
            }
            ret += "\n";
        }
        return ret;
    }

    public static int stringToInt(String x) {
        x = x.toLowerCase();
        return x.chars().map(u -> u - 96).sum();
    }

    public static int sumOfDigits(BigInteger x) {
        int sum = 0;
        for (String s : x.toString().split("")) {
            sum += Integer.valueOf(s);
        }
        return sum;
    }

    public static BigInteger factorial(int x) {
        BigInteger r = new BigInteger(x + "");
        if (x > 2) {
            return r.multiply(factorial(x - 1));
        } else {
            return new BigInteger("2");
        }
    }

    public static long movesInGrid(int right, int down) {
        long sum = 0;
        if (right < 20) {
            sum += movesInGrid(right + 1, down);
        }
        if (down < 20) {
            sum += movesInGrid(right, down + 1);
        }
        if (down == 20 && right == 20) {
            return 1;
        }
        return sum;
    }

    public static int getChain(long x) {
        int cunt = 0;
        while (x > 1) {
            if (x % 2 == 0) {
                x = x / 2;
                cunt++;
            } else {
                x = x * 3 + 1;
                cunt++;
            }
            // System.out.println(x);
        }
        return cunt;
    }

    public static String nineProblem() {
        for (int a = 1; a < 1000; a++) {
            for (int b = 1; b < 1000; b++) {
                int c = 1000 - a - b;
                if (a * a + b * b == c * c) {
                    // It is now implied that b < c, because we have a > 0
                    return a + "+" + b + "+" + c;
                }
            }
        }
        return null;
    }

    /**
     * used for eight problem
     *
     * @return
     */
    public static Long eightProblem(String x) {
        long numeber = -1;
        long cunt;
        for (int z = 0; z < x.length() - 12; z++) {
            cunt = 1;
            for (int o = 0; o <= 12; o++) {
                cunt = cunt * Integer.valueOf(x.charAt(o + z) + "");
                // System.out.println(o+","+x.charAt(o+z)+","+cunt);
            }
            // System.out.println(cunt);
            if (cunt > numeber) {
                numeber = cunt;
            }
        }
        return numeber;
    }

    /**
     * using to solve problem number 4
     *
     * @param x
     * @return
     */
    public static boolean isNumberPalindromic(Long x) {
        String txt = x + "";
        for (int y = 0; y < (txt.length() % 2 == 0 ? txt.length() / 2 : txt.length() / 2 + 1); y++) {
            if (txt.charAt(y) != txt.charAt(txt.length() - y - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String loadDataFromFile(String url) {
        String readed = "";
        try (FileReader f = new FileReader(new File(url))) {

        }
        return readed;

    }

}
