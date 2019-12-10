import java.io.Serializable;

public class Gnol implements Rasa {

	@Override
	public String toString() {
		return "Gnol";
	}
	@Override
	public double silaBonus(int sila) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double obratnostBonus(int obratnost) {
		// TODO Auto-generated method stub
		return (obratnost/100.0)*5.0;
	}

	@Override
	public double inteligenceBonus(int inteligence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double odolnostBunus(int odolnost) {
		// TODO Auto-generated method stub
		return (odolnost/100.0)*5.0;
	}

}
