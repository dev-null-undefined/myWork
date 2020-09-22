import java.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Auto a=new Auto("Super rychle auto",110.5f,Pohon.BENZIN,2, new Date(92,Calendar.FEBRUARY,2));
			Auto b=new Auto("ASuper rychle auto",115.5f,Pohon.BENZIN,2, new Date(92,Calendar.FEBRUARY,4));
//			Auto c=new Auto("ZSuper rychle auto",112.5f,Pohon.BENZIN,2, new Date(92, Calendar.FEBRUARY,1));
			Auto c=new Auto("ZSuper outak",112.5f,Pohon.BENZIN,2, new Date(92, Calendar.FEBRUARY,3));
			DrazebniDatabaze data=new DrazebniDatabaze();
			data.Pridej(a);
			data.Pridej(b);
			data.Pridej(c);
//			System.out.println(data.GetSerazene());
//			System.out.println(data.Hledej("ASuper rychle auto").toString());
			data.ZacniDrazbu("ZSuper outak");
			data.ZacniDrazbu("ZSuper outak");
			data.ZacniDrazbu("ZSuper outak");
			System.out.println("data.getFrontaDrazeb() = " + data.getFrontaDrazeb());
			System.out.println("data.getAktualniDrazba().toString() = " + data.getAktualniDrazba().toString());
			data.getAktualniDrazba().Prihod(new Nabidka(new Uzivatel("Martin",1),115.2f));
			System.out.println("data.getAktualniDrazba().toString() = " + data.getAktualniDrazba().toString());
			data.getAktualniDrazba().Prihod(new Nabidka(new Uzivatel("Martin",1),115.3f));
			System.out.println("data.getAktualniDrazba().toString() = " + data.getAktualniDrazba().toString());
			System.out.println("data.getAktualniDrazba().NejvysiNabidka() = " + data.getAktualniDrazba().NejvysiNabidka());
//			Queue<Integer> queue = new ArrayDeque<>();
//			queue.add(1);
//			queue.add(10);
//			queue.add(12);
//			queue.add(15);
//			System.out.println("queue = " + queue);
//			for (Integer integer : queue) {
//				System.out.println(integer);
//			}
//			for (int i = 0; i < 4; i++) {
//				System.out.println("queue = " + queue.element());
//			}
//			Stack<Integer> queue = new Stack<>();
//			queue.add(1);
//			queue.add(10);
//			queue.add(12);
//			queue.add(15);
//			System.out.println("queue = " + queue);
//			for (Integer integer : queue) {
//				System.out.println(integer);
//			}
//			for (int i = 0; i < 4; i++) {
//				System.out.println("queue = " + queue.peek());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
