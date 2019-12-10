public class Bojovnik extends Postava{

	@Override
	public double getUtok() {
		// TODO Auto-gsenerated method stub
		return 3*getSila();
	}

	@Override
	public double getObrana(Postava nepritel) {
		// TODO Auto-generated method stub
		return nepritel.getVlastnost(this)+getOdolnost();
	}

	@Override
	public double getVlastnost(Postava postava) {
		// TODO Auto-generated method stub
		return postava.getSila();
		//postava je cokoliv - bojovnik, mag, pruzkumnik
		//bojuje s nepritelem, ktery je bojovnik, takze se brani svoji silou
	}

}