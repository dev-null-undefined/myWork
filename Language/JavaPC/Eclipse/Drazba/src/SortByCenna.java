import java.util.Comparator;

public class SortByCenna implements Comparator<Auto> {

	@Override
	public int compare(Auto arg0, Auto arg1) {
		return Math.round(arg0.getCenna()-arg1.getCenna());
	}

}