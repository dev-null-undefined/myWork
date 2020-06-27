import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random r = new Random();
		ListInteger a=new ListInteger();
	    a.add(r.nextInt(10));
		a.add(r.nextInt(10));
		a.add(r.nextInt(10));
		a.add(r.nextInt(10));
		a.add(r.nextInt(10));
		a.add(r.nextInt(10));
		a.serad();
		System.out.print(a);
		ArrayList<String> aa=new ArrayList<>();	
		}

}
