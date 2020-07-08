import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int imput;
		int pocetcisel=0;
		int soucet=0;
		int nejmin=Integer.MAX_VALUE;//--> max hodnota jak u 2
		System.out.println("Zadavejte nezáporná čísla pokud chcete ukončít zadejte záporná.");
		while(true) {
			System.out.print(" - ");
			imput=in.nextInt();
			if(imput>=0) {
				pocetcisel++;
				soucet+=imput;
				if(nejmin>imput) {
					nejmin=imput;
				}
			}else {
				System.out.println("");
				break;//ukončí cyklus while
			}
		}
		System.out.println("Pocet nezáporných čísel je: "+pocetcisel);
		if(pocetcisel!=0) {
			System.out.println("Prumer nezáporných čísel je: "+soucet/pocetcisel);
			System.out.println("Nejmenší nezáporných číslo je: "+nejmin);
		}else {
			System.out.println("Nelze udělat pruměr nezáporných čísel nezadal jsi ani jedno nezáporných číslo.");
			System.out.println("Nejmenší nezáporných číslo nelze určit žádné číslo nebylo zaznamenáno");
		}
		in.close();
	}
}
