import java.util.*; 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(":"+longestSubStringWihtoutRepeating("abcabcbb"));
	}
	public static int longestSubStringWihtoutRepeating(String inputString) {
		int start = 0;
		int maximum = 0;
		HashSet<Integer> charIn=new HashSet<>();
		for(int i=0;i<inputString.length();i++) {
			if(charIn.contains((int) inputString.charAt(i))) {
				charIn.remove((int) inputString.charAt(start++));
			}
			charIn.add((int)inputString.charAt(i));
			maximum=Math.max(charIn.size(), maximum);
		}
		return maximum;
	}

}
