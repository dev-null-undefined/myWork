
public class Main {

	public static void main(String[] args) {
		Spojak<String> spojak=new Spojak<>(null);

		spojak.addFirst("hojda");
		spojak.addFirst("hojda");
		spojak.addFirst("hojda");
		spojak.addFirst("hojda");

		System.out.println(spojak.size());
		for(Element s:spojak) {
			System.out.println(s.value);
		}
	}

}
