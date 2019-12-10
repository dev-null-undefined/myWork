package Ukol_1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String []args) {
		Scanner in = new Scanner (System.in);
		System.out.print("Zadejte polomer koule: ");
		try {
			while(true) {
				double polo=in.nextDouble();
				Koule jedna = new Koule(polo,in);
				System.out.format("Koule m� objem %.2f a povrch %.2f%n",jedna.obejm(),jedna.povrch());
				System.out.print("Zadejte polomer koule: ");
			}
		}catch(InputMismatchException e) {
			System.out.print("Chyba: "+e+"\nProto konec.");
			for(int x=0;x<5;x++) {
				System.out.print(".");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
				}
			}
			System.exit(0);
			
		}
	}

}
