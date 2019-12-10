package Ukl_1;
import java.util.Scanner;

public class Hora {

	Scanner sc = new Scanner(System.in);
	
	private String JmenoHory;
	private int VyskaHory;
 
	public Hora(String Jmeno, int VyskaHory) {
		while(VyskaHory < 0) {
			System.out.print("Zadej kladnou hodnotu: ");
			VyskaHory = sc.nextInt();
		}
		this.JmenoHory = Jmeno;
		this.VyskaHory = VyskaHory;
	}

	public String getJmenoHory() {
		return JmenoHory;
	}

	public int getVyskaHory() {
		return VyskaHory;
	}

	
	
}
