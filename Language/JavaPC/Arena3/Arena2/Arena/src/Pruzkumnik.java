
public class Pruzkumnik extends Postava{

	@Override
	public double getUtok() {
		// TODO Auto-generated method stub
		return 3.0*getObratnost();
	}

	@Override
	public double getObrana(Postava nepritel) {
		// TODO Auto-generated method stub
		return nepritel.getVlastnost(this)+getOdolnost();
	}

	@Override
	public double getVlastnost(Postava postava) {
		// TODO Auto-generated method stub
		return postava.getObratnost();
	}
	public Pruzkumnik(String jmeno, int level, int sila, int inteligence, int obratnost, int odolnost, int zkusenosti,
			Rasa rasa) {
		super(jmeno, level, sila, inteligence, obratnost+3, odolnost, zkusenosti, rasa);
		// TODO Auto-generated constructor stub
	}

	public Pruzkumnik(String jmeno, int sila, int inteligence, int obratnost, int odolnost, Rasa rasa) {
		super(jmeno, sila, inteligence, obratnost+3, odolnost, rasa);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Hrac (Pruzkumnik):" + jmeno +", HP="+hp+ ", level=" + level + ", sila=" + this.getSila() + ", inteligence=" + this.getInteligence()
				+ ", obratnost=" + this.getObratnost() + ", odolnost=" + this.getOdolnost() + ", zkusenosti=" + zkusenosti + ", rasa="
				+ rasa.toString();
	}
	public String toSaveString() {
		return "Hrac(Pruzkumnik):" +jmeno +System.lineSeparator()+ "HP="+hp+System.lineSeparator()+ "level=" + level +System.lineSeparator()+ "sila=" + sila + System.lineSeparator()+"inteligence=" + inteligence
				+System.lineSeparator()+ "obratnost=" + obratnost +System.lineSeparator()+ "odolnost=" + odolnost +System.lineSeparator()+ "zkusenosti=" + zkusenosti +System.lineSeparator()+ "rasa="
				+ rasa.toString();
	}
	
}
