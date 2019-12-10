
public class ryba {
private String jmeno;
public Interval inte;
public ryba(String jmeno,int h,int d) {
	this.jmeno=jmeno;
	this.inte=new Interval(h,d);
}

public String getJmeno() {
	return jmeno;
}
public void setJmeno(String jmeno) {
	this.jmeno = jmeno;
}
public Interval getInte() {
	return inte;
}

}
