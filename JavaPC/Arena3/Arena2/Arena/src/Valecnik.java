
public class Valecnik extends Postava{

	
	@Override
	public double getUtok() {
		// TODO Auto-generated method stub
		return 3.0*getSila();
	}

	@Override
	public double getObrana(Postava enemy) {
		// TODO Auto-generated method stub
		return enemy.getVlastnost(this)+getOdolnost();
	}

	@Override
	public double getVlastnost(Postava postava) {
		// TODO Auto-generated method stub
		return postava.getSila();
		//postava je cokoliv - bojovnik, mag, pruzkumnik
		//bojuje s nepritelem, ktery je bojovnik, takze se brani svoji silou
	}

	public Valecnik(String jmeno, int level, int sila, int inteligence, int obratnost, int odolnost, int zkusenosti,
			Rasa rasa) {
		super(jmeno, level, sila+3, inteligence, obratnost, odolnost, zkusenosti, rasa);
		// TODO Auto-generated constructor stub
	}

	public Valecnik(String jmeno, int sila, int inteligence, int obratnost, int odolnost, Rasa rasa) {
		super(jmeno, sila+3, inteligence, obratnost, odolnost, rasa);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Hrac (Valecnik):" + jmeno +", HP="+hp+ ", level=" + level + ", sila=" + this.getSila() + ", inteligence=" + this.getInteligence()
				+ ", obratnost=" + this.getObratnost() + ", odolnost=" + this.getOdolnost() + ", zkusenosti=" + zkusenosti + ", rasa="
				+ rasa.toString();
	}
	public String toSaveString() {
		return "Hrac(Valecnik):" +jmeno +System.lineSeparator()+ "HP="+hp+System.lineSeparator()+ "level=" + level +System.lineSeparator()+ "sila=" + sila + System.lineSeparator()+"inteligence=" + inteligence
				+System.lineSeparator()+ "obratnost=" + obratnost +System.lineSeparator()+ "odolnost=" + odolnost +System.lineSeparator()+ "zkusenosti=" + zkusenosti +System.lineSeparator()+ "rasa="
				+ rasa.toString();
	}

}