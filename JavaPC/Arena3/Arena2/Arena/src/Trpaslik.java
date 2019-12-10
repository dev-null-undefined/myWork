import java.io.Serializable;

public class Trpaslik implements Rasa {

	@Override
	public String toString() {
		return "Trpaslik";
	}
	
	@Override
	public double silaBonus(int sila) {
		// TODO Auto-generated method stub
		return (sila/100.0)*7.0;
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
		return (odolnost/100.0)*3.0;
	}

}
