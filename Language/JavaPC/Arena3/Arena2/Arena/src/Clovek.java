import java.io.Serializable;

public class Clovek implements Rasa{

	@Override
	public String toString() {
		return "Clovek";
	}
	@Override
	public double silaBonus(int sila) {
		// TODO Auto-generated method stub
		return (sila/100.0)*3.0;
	}

	@Override
	public double obratnostBonus(int obratnost) {
		// TODO Auto-generated method stub
		return (obratnost/100.0)*4.0;
	}

	@Override
	public double inteligenceBonus(int inteligence) {
		// TODO Auto-generated method stub
		return (inteligence/100.0)*3.0;
	}

	@Override
	public double odolnostBunus(int odolnost) {
		// TODO Auto-generated method stub
		return 0;
	}

}
