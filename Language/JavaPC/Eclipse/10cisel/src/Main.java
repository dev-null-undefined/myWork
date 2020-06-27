import java.util.Scanner;//jinak scanner prostě nebude fungovat

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);// scanner
		int x = 10;  //--> počet kroku 
		int imput;     //-->  vstup
		int pocetsudy = 0;//-->  Když chceme přičítat musím incializovat hodnotu
	                      //-->  neboli musíme říct kolika se rovná
		int sudysoucet=0;
		int soucet = 0; 
		int jednocifra=0;
		int nejvíc=Integer.MIN_VALUE;//nejmenší možné číslo také lze zapsat pomocí = -2147483648; uživatel mohl zadat záporné číslo
		System.out.println("Zadejte deset čísel: ");
		while (x != 0) {
			System.out.print(" - ");
			x--;       //-->  odečte od x 1 dá se napsat taky jako x+=-1; nebo jako x=x-1;
			imput = in.nextInt();   //-->  imput od uživatele
			if (imput % 2 == 0) {   //-->  %dělá zbytek po celočíselném dělění a neboli
									//-->  když je číslo sudé tak po vydělení dvěma není zbytek
				pocetsudy++;        //-->  pocetsudy=pocetsudy+1;
				sudysoucet+=imput;//--> přičte k studým imput
				                  //--> protože aritmetický průměr se dělá vydělením součtu počtem členů
			}
			if(imput<=9) {//--> je jednociferné když je menší než 9 nebo 9
				jednocifra++;
			}
			if(nejvíc<imput) {//--> největší je vždy větší než to před ním
				nejvíc=imput;
			}
			soucet+=imput;//--> secte imput a soucet a uloží do součet je to jako součet=soucet+imput;
			
		}
		System.out.println("Součet všech čísel je: "+soucet);
		if(pocetsudy!=0) {//nulou dělit nelze!!!!
		System.out.println("Aritmetický průměr sudých čísel je:"+sudysoucet/pocetsudy);
		}else {
			System.out.println("Žádné sudé číslo.");
		}
		if(jednocifra!=0) {//for funny
			System.out.println("Počet jednociferných čísel je:"+jednocifra);
     	}else {
			System.out.println("Žádné jednociferné číslo.");
		}
		System.out.println("Největší číslo je: "+nejvíc);
		System.out.println("KONEC PROGRAMU");
		in.close();//--> jen pro pana brichtiče
	}

}
