import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		StringInside a=new StringInside(new String("hello"));
		setFive(a);
		System.out.println(a.s);
	}
	public static void setFive(StringInside b){
		b.s="How are you doing";
	}
}
class StringInside{
	public String s;
	public StringInside(String s){
		this.s=s;
	}
}
