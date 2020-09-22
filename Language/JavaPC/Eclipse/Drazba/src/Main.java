import java.util.Date;

public class Main {

	public static void main(String[] args) {
		try {
			Auto a=new Auto(new Date(), 1, 100.5f, "Super rychle auto", Pohon.Benzin);
			Auto b=new Auto(new Date(92,1,1), 3, 110.5f, "ASuper rychle auto 2", Pohon.Benzin);
			Auto c=new Auto(new Date(93,2,3), 2, 112.5f, "ZSuper rychle auto 3", Pohon.Benzin);
			DrazebniDatabaze data=new DrazebniDatabaze();
			data.Pridej(a);
			data.Pridej(b);
			data.Pridej(c);
			System.out.println(data.GetSerazene());
			System.out.println(data.Hledej("ASuper rychle auto 2").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
