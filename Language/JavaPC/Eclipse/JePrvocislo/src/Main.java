import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.*;

public class Main {
    public static void mezery(int pocet) {
    	while (pocet!=0) {
    		System.out.print(" ");
    		pocet+=-1;
    	}
    }
    public static void vypis(ArrayList<Integer> list) {
    	int mnoho=list.size();
    	int x=0;
    	while(x+1<=mnoho) {
    		System.out.print(list.get(x)+"*");
    		x++;
    	}
    }
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList <Integer> prvo = new ArrayList <Integer> ();
		ArrayList <Integer> delitel = new ArrayList <Integer>();
		while (true) {
			System.out.print("Zadejte číslo: ");
			try {
				int Imput = in.nextInt();
				if (Imput > 0) {
					int y = 2;
					boolean repeatTest = true;
					boolean isPrvo = true;
					delitel.clear();
					delitel.add(1);
					while (repeatTest) {
						if (y < Imput) {
								if (Imput % y != 0) {
									y++;
								} else {
									if(isPrvo) {
										System.out.println("Není prvočíslo.");
										System.out.print("Je dělitelné číslem: ");
										isPrvo = false;
									}
									delitel.add(y);
									y++;
								}
							
						} else {
							
							if(isPrvo) {
							System.out.println("Číslo " + Imput + " je prvočíslo.");
							repeatTest = false;
							}else {
								delitel.add(Imput);
								int pocetdelitelu=delitel.size();
								while(pocetdelitelu!=0) {
									System.out.print(" "+delitel.get(pocetdelitelu-1)+",");
									pocetdelitelu+=-1;
								}
								System.out.println("\nRozklad na prvočísla:");
								int pomocny=Imput;
								int z=2;
								int pocetMezer=0;
								prvo.clear();
								while(z<pomocny) {
									if(pomocny%z==0) {
										mezery(pocetMezer);
										pocetMezer++;
										System.out.print("|");
										vypis(prvo);
										System.out.print(z+"*"+pomocny/z+"\n");
										prvo.add(z);
										pomocny=pomocny/z;
									}else {
										z++;
									}
								}
								repeatTest=false;
							}
						}
					}
				} else {
					System.out.println("Prosím zadejte číslo od 0 do  2,147,483,647");
				}
			} catch (InputMismatchException e) {
				System.out.println("Prosím zadejte číslo od 0 do  2,147,483,647");
				in.nextLine();
			} catch (NoSuchElementException e) {
			}

		}
	}

}
