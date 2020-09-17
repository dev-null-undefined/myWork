import java.util.Comparator;

public class SortByPocetDveri implements Comparator<Auto> {

	@Override
	public int compare(Auto arg0, Auto arg1) {
		return arg0.getPocetDveri()-arg1.getPocetDveri();
	}

}
