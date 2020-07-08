
public class Interval {
	private int horni;
	private int dolni;

	public Interval(int horni, int dolni) {
		if (horni > dolni) {
			this.horni = dolni;
			this.dolni = horni;
		} else {
			this.horni = horni;
			this.dolni = dolni;
		}

	}
    public  boolean isIn (int teplo) {
    	if(teplo<=dolni && teplo>=horni) {
    		return true;
    	} else {
    		return false;
    	}
    }
	public int getHorni() {
		return horni;
	}

	public void setHorni(int horni) {
		this.horni = horni;
	}

	public int getDolni() {
		return dolni;
	}

	public void setDolni(int dolni) {
		this.dolni = dolni;
	}

}
