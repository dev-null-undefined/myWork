
public class main {

	public static void main(String[] args) {
		long avg = 0;
		long values = 0;
		for(int o = 0; o<10; o++) {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 1000000000; i++) {
				values+=division(1000,i);// i/0=4712.7 100/i=5745.3
//				values+=divisionWithTry(1000, i);// i/0=7189.5 100/i=6940.7
			}
			long end = System.currentTimeMillis();
			avg += end - start;
			System.out.println(end - start);
		}
		System.out.println(avg/10.0f);
	}

	public static double divisionWithTry(int a, int b) {
		double result;
		try {
			result = a / (double) b;
		} catch (Exception e) {
			result = 0.0;
		}
		return result;
	}

	public static double division(int a, int b) {
		double result = 0.0;
		if (b != 0) {
			result = a / (double) b;
		}
		return result;
	}

}
