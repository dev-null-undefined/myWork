import java.util.Scanner;

public class Drak {
	private int hlavy;
	private String jmeno;
	public Drak(int hlavy, String jmeno) {
		Scanner in = new Scanner(System.in);
		while(hlavy<=0) {
			System.out.print("Zadejte znovu počet hlav: ");
			hlavy=in.nextInt();
		}
		this.hlavy = hlavy;
		this.jmeno = jmeno;
		in.close();
		
	}
	public Drak(String jmeno) {
		this.jmeno=jmeno;
	}
	public int getHlavy() {
		return hlavy;
	}
	public void setHlavy(int hlavy) {
		this.hlavy = hlavy;
	}
	public String getJmeno() {
		return jmeno;
	}
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
	

}
