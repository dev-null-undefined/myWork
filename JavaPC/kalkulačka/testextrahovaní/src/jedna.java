import java.util.Scanner;

public class jedna {
	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static Double number() {
		boolean Int;
		System.out.print("Input some number: ");
		Scanner in = new Scanner(System.in);
		String text = in.nextLine();
		Int = isInteger(text);
		if (Int == true) {
			return Double.valueOf(text);
		} else {
			boolean Duble = isDouble(text);
			if (Duble == true) {
				return Double.valueOf(text);
			} else {
				System.out.println(
						"information(I can not calculate with this, please use number.nuber no number,number thanks)");
				System.exit(0);
				return 0.0;
			}
		}
	}

	public static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static void calculator(double jedna, double dva, int what) {
		Double vysledek;
		switch (what) {
		case 1:
			vysledek = jedna + dva;
			System.out.println("Sum is: " + vysledek);
			break;
		case 2:
			vysledek = jedna - dva;
			System.out.println("Deduct is: " + vysledek);
			break;
		case 3:
			vysledek = jedna * dva;
			System.out.println("Multiply is: " + vysledek);
			break;
		case 4:
			vysledek = jedna / dva;
			if (dva==0) {
				System.out.println("Denominator can not be 0");
			}
			else {
			System.out.println("Devide is: " + vysledek);
			break;
			}
		}
	}

	public static void main(String[] args) {
		boolean repeat = true;
		while (repeat == true) {
			System.out.format("You can: %n  1)sum%n  2)deduct%n  3)multiply%n  4)devide%nYour option: ");
			Scanner in = new Scanner(System.in);
			String option = in.nextLine();
			Boolean isoption = isInteger(option);
			if (isoption == true) {
				int optionint = Integer.valueOf(option);
				if (optionint <= 4 && optionint >= 1) {
					Double jedna = number();
					Double dva = number();
					System.out.println("---------------------------------------------");					
					calculator(jedna, dva, optionint);
					System.out.println("---------------------------------------------");

				} else {
					System.out.println("---------------------------------------------");
					System.out.println("Please input number 1-4");
					System.out.println("---------------------------------------------");
				}
			} else {
				System.out.println("---------------------------------------------");
				System.out.println("Please input integer(number)");
				System.out.println("---------------------------------------------");
			}
		}
	}
}