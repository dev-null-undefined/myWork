package scaner;

public class koule {
	private double r;
	private double b=3.14;
	public koule (double a) {
		r=a;
	}
	public double getObjem() {
	   	double e=4/3*b*r*r*r;
	   	return e;
	}
	public double getPovrch () {
		double f=4*b*r*r;
		return f;
	}

}
