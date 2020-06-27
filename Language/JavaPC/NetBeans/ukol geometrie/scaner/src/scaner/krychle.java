package scaner;

public class krychle {
	private Double b;
	public krychle (double a) {
		b=a;
	}
    public double getObjem() {
    	double c=b*b*b;
    	return c;
    }
    public double getPovrch() {
    	double d=6*b*b;
    	return d;
    }
}
