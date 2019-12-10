import java.io.Serializable;

public class Troll implements Rasa{

	@Override
	public String toString() {
		return "Troll";
	}
	@Override
	public double silaBonus(int sila) {
		// TODO Auto-generated method stub
		return (sila/100.0)*10.0;
	}

	@Override
	public double obratnostBonus(int obratnost) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double inteligenceBonus(int inteligence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double odolnostBunus(int odolnost) {
		// TODO Auto-generated method stub
		return 0;
	}

}
