
import java.io.Serializable;

public abstract class Postava {

    protected String jmeno;
    protected int level;
    protected int sila;
    protected int inteligence;
    protected int obratnost;
    protected double hp = 100.0;
    protected int skillPoint = 0;
    protected int odolnost;
    protected int zkusenosti;
    protected Rasa rasa;

    public void odolnostPlus(int x) {
        this.odolnost += x;
    }

    public void inteligencePlus(int x) {
        this.inteligence += x;
    }

    public void silaPlus(int x) {
        this.sila += x;
    }

    public void obratnostPlus(int x) {
        this.obratnost += x;
    }

    public Postava() {
    }

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

    @Override
    public String toString() {
        return "Hrac :" + jmeno + ", HP=" + hp + ", level=" + level + ", sila=" + this.getSila() + ", inteligence=" + this.getInteligence()
                + ", obratnost=" + this.getObratnost() + ", odolnost=" + this.getOdolnost() + ", zkusenosti=" + zkusenosti + ", rasa="
                + rasa.toString();
    }

    public String toSaveString() {
        return "Hrac:" + jmeno + System.lineSeparator() + "HP=" + hp + System.lineSeparator() + "level=" + level + System.lineSeparator() + "sila=" + sila + System.lineSeparator() + "inteligence=" + inteligence
                + System.lineSeparator() + "obratnost=" + obratnost + System.lineSeparator() + "odolnost=" + odolnost + System.lineSeparator() + "zkusenosti=" + zkusenosti + System.lineSeparator() + "rasa="
                + rasa.toString();
    }

    public Postava(String jmeno, int sila, int inteligence, int obratnost, int odolnost, Rasa rasa) {
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

    public void setSila(int d) {
        this.sila = d;
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
        return odolnost + rasa.odolnostBunus(odolnost);
    }

    public void setOdolnost(int odolnost) {
        this.odolnost = odolnost;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public abstract double getUtok();

    public abstract double getObrana(Postava nepritel);

    public abstract double getVlastnost(Postava postava);

    public int getLevel() {
        // TODO Auto-generated method stub
        return level;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }

    public void pridejZk(int i) {
        zkusenosti += i;
        if (zkusenosti >= 100 * level) {
            level++;
            skillPoint += 2;
            zkusenosti -= 100 * level;
            this.pridejZk(0);
        }
		// TODO Auto-generated method stub

    }

    public void setHpFullHealth() {
        this.hp=100*level;
    }
    public void resetSkillPoint(){
        skillPoint=0;
    }
}
