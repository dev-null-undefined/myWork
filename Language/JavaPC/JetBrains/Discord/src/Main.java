public class Main {
    public static void main(String[] a) {
        int p = 96;
        for (var b : a[0].toCharArray()) {
            p++;
            if (b != '>') {
                System.out.print((char) (b == '.' ? p : p - 32));
                p = 96;
            }
        }
    }
}