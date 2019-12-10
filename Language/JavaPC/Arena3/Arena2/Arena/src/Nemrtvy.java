import java.io.Serializable;

public class Nemrtvy implements Rasa{

	@Override
	public String toString() {
		return "Nemrtvy";
	}
	@Override
	public double silaBonus(int sila) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double obratnostBonus(int obratnost) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double inteligenceBonus(int inteligence) {
		// TODO Auto-generated method stub
		return (inteligence/100.0)*10.0;
	}

	@Override
	public double odolnostBunus(int odolnost) {
		// TODO Auto-generated method stub
		return 0;
	}

}
