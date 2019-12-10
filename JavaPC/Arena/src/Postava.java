public abstract class Postava {
private String jmeno;
private int level;
private int sila;
private int inteligence;
private int obratnost;
private int odolnost;
private int zkusenosti;
private Rasa rasa;
 

public Postava () {}
public Postava(String jmeno, int level, int sila, int inteligence, int obratnost, int odolnost, int zkusenosti,
		Rasa rasa) {
	super();
	this.jmeno = jmeno;
	this.level = level;
	this.sila = sila;
	this.inteligence = inteligence;
	this.obratnost = obratnost;
	this.odolnost = odolnost;
	this.zkusenosti = zkusenosti;
	this.rasa = rasa;
}

public Postava(String jmeno, int sila, int inteligence, int obratnost, int odolnost,
		Rasa rasa) {
	super();
	this.jmeno = jmeno;
	this.level = 1;
	this.sila = sila;
	this.inteligence = inteligence;
	this.obratnost = obratnost;
	this.odolnost = odolnost;
	this.zkusenosti = 0;
	this.rasa = rasa;
}



public double getSila() {
	return sila + rasa.silaBonus(sila);
}



public void setSila(int sila) {
	this.sila = sila;
}

public double getInteligence() {
	return inteligence + rasa.inteligenceBonus(inteligence);
}
public void setInteligence(int inteligence) {
	this.inteligence = inteligence;
}
public double getObratnost() {
	return obratnost + rasa.obratnostBonus(obratnost);
}
public void setObratnost(int obratnost) {
	this.obratnost = obratnost;
}
public double getOdolnost() {
	return odolnost+rasa.obratnostBonus(obratnost);
}
public void setOdolnost(int odolnost) {
	this.odolnost = odolnost;
}
public abstract double getUtok();
public abstract double getObrana(Postava nepritel);
public abstract double getVlastnost(Postava postava);


}