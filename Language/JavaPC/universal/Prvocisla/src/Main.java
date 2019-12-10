import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String scene;
		for(int x = 0; x!=6;x++) {
		scene=(new Scanner(System.in)).nextLine();
		switch(scene) {
		case "ahoj":
			System.out.print("ahoj");
			break;
		case "kokot":
			System.out.print("kokot");
			break;
		case "debil":
			System.out.print("debil");break;
			
		case "ne":
			System.out.print("ne");break;
		case "ano":
			System.out.print("ano");break;
		}
		}
	}

}
