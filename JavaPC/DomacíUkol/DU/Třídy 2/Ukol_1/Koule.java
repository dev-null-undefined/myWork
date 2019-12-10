package Ukol_1;

import java.util.Scanner;

public class Koule {
	private double polomer;
	private double pi=Math.PI;
	private double polomerNa2;
	public Koule(double polo,Scanner in) {
		while (polo<0) {
			System.out.print("Zadejte kladný poloměr");
			polo=in.nextDouble();
		}
		polomer=polo;
		polomerNa2=polo*polo;
	}
	public double getPolomer() {
		return polomer;
	}
	public double obejm() {
		return 1.33333333333*pi*polomerNa2*polomer;
	}
	public double povrch() {
		return 4*pi*polomerNa2;
	}
		

}
