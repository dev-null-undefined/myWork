import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.print("Vitejte v akvarku\nZadejte pocet ryb: ");
		int pocetryb=in.nextInt();
		//in.nextLine(); neni zapotreby pri nacitani dvou intu se nemusi cistit buffer (za sebou pozn kose)
		System.out.print("Jak moc teply je akvarko(bez dvojsmyslu):");
		akvarium ak=new akvarium(in.nextInt(),pocetryb);
		in.nextLine();
		
        for(int i=0;i<pocetryb;i++) {
        	System.out.println("Jmeno rybky: ");
        	String jmn=in.nextLine();
        	System.out.println("Max teplota: ");
        	int h=in.nextInt();
        	System.out.println("Min teplota: ");
        	int d=in.nextInt();
        	in.nextLine();
        	ryba rb=new ryba(jmn,h,d);
        	if(ak.addRyba(rb)==false) {
        	System.out.println("Ryba zde nemuze prezit ");
             i--;
        	}
        //for(int d=0;d)
        }
	}

}
