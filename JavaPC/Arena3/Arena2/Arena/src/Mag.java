
public class Mag extends Postava{

	@Override
	public double getUtok() {
		// TODO Auto-generated method stub
		return 3.0*getInteligence();
	}

	@Override
	public double getObrana(Postava nepritel) {
		// TODO Auto-generated method stub
		return nepritel.getVlastnost(this)+getOdolnost();
	}

	@Override
	public double getVlastnost(Postava postava) {
		// TODO Auto-generated method stub
		return postava.getInteligence();
	}
	
	public Mag(String jmeno, int level, int sila, int inteligence, int obratnost, int odolnost, int zkusenosti,
			Rasa rasa) {
		super(jmeno, level, sila, inteligence+3, obratnost, odolnost, zkusenosti, rasa);
		// TODO Auto-generated constructor stub
	}

	public Mag(String jmeno, int sila, int inteligence, int obratnost, int odolnost, Rasa rasa) {
		super(jmeno, sila, inteligence+3, obratnost, odolnost, rasa);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Hrac (Mag):" + jmeno +", HP="+hp+ ", level=" + level + ", sila=" + this.getSila() + ", inteligence=" + this.getInteligence()
				+ ", obratnost=" + this.getObratnost() + ", odolnost=" + this.getOdolnost() + ", zkusenosti=" + zkusenosti + ", rasa="
				+ rasa.toString();
	}
	public String toSaveString() {
		return "Hrac(Mag):" +jmeno +System.lineSeparator()+ "HP="+hp+System.lineSeparator()+ "level=" + level +System.lineSeparator()+ "sila=" + sila + System.lineSeparator()+"inteligence=" + inteligence
				+System.lineSeparator()+ "obratnost=" + obratnost +System.lineSeparator()+ "odolnost=" + odolnost +System.lineSeparator()+ "zkusenosti=" + zkusenosti +System.lineSeparator()+ "rasa="
				+ rasa.toString();
	}
}
