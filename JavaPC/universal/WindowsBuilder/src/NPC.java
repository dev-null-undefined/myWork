import java.util.Random;

public class NPC {
	private int HP, DM;
	private String Jmeno;

	public void setAll(int hP, int dM, String jmeno) {
		HP = hP;
		DM = dM;
		Jmeno = jmeno;
	}

	public NPC(int Option) {
		Random r = new Random();
		switch (Option) {
		case 1:
			switch (r.nextInt(10) + 0) {
			case 1:
				this.setAll(7, 5, "Divočák ");
				break;
			case 2:
				this.setAll(9, 3, "Kočka ");
				break;
			case 3:
				this.setAll(5, 6, "Vlk ");
				break;
			case 4:
				this.setAll(4, 2, "Opička ");
				break;
			case 5:
				this.setAll(9, 4, "Hroch ");
				break;
			case 0:
				this.setAll(1, 9, "Pavouk ");
				break;
			case 6:
				this.setAll(5, 2, "Orel ");
				break;
			case 7:
				this.setAll(7, 9, "Tygr ");
				break;
			case 8:
				this.setAll(8, 7, "MEDVĚD ");
				break;
			case 9:
				this.setAll(3, 3, "Had ");
				break;
			case 10:
				this.setAll(4, 1, "Ovce ");
				break;
			default:
				this.setAll(1, 1, "error ");
			}
			switch (r.nextInt(15) + 0) {
			case 1:
				HP -= r.nextInt(6) + 1;
				if (HP >= 1) {
					Jmeno = Jmeno + "zraněné ";
				} else {
					Jmeno = Jmeno + "mrtvý ";
					HP = 0;
				}
				break;
			case 2:
				DM += r.nextInt(2) + 1;
				DM++;
				Jmeno = Jmeno + "naštvaný ";
			}
			break;
		case 2:
			switch (r.nextInt(10) + 0) {
			case 1:
				this.setAll(15, 5, "Tma ");
				break;
			case 2:
				this.setAll(12, 12, "Kouzelník ");
				break;
			case 3:
				this.setAll(10, 10, "Krysa ");
				break;
			case 4:
				this.setAll(25, 5, "Golem ");
				break;
			case 5:
				this.setAll(15, 19, "Nemrtví ");
				break;
			case 0:
				this.setAll(8, 20, "Pavouk ");
				break;
			case 6:
				this.setAll(0, 18, "Plyn ");
				break;
			case 7:
				this.setAll(10, 20, "Člověk ");
				break;
			case 8:
				this.setAll(20, 10, "MEDVĚD ");
				break;
			case 9:
				this.setAll(2, 30, "Netopíři ");
				break;
			case 10:
				this.setAll(0, 0, "Nic ");
				break;
			default:
				this.setAll(1, 1, "error ");
			}
			switch (r.nextInt(15) + 0) {
			case 1:
				HP -= r.nextInt(2) + 1;
				if (HP >= 1) {
					Jmeno = Jmeno + "zraněný/á ";
				} else {
					Jmeno = Jmeno + "mrtvý/á ";
				}
				break;
			case 2:
				DM += r.nextInt(2) + 1;
				DM++;
				Jmeno = Jmeno + "naštvaný/á ";
			}
			break;
		case 3:
			switch (r.nextInt(10) + 0) {
			case 1:
				this.setAll(40, 50, "Gorila ");
				break;
			case 2:
				this.setAll(40, 60, "Jaguár ");
				break;
			case 3:
				this.setAll(10, 100, "Anakonda ");
				break;
			case 4:
				this.setAll(100, 100, "DRAK ");
				break;
			case 5:
				this.setAll(60, 40, "Kentaur ");
				break;
			case 0:
				this.setAll(40, 60, "Sfinga ");
				break;
			case 6:
				this.setAll(80, 54, "Kyklop ");
				break;
			case 7:
				this.setAll(44, 40, "Groot ");
				break;
			case 8:
				this.setAll(10, 20, "Slizoun ");
				break;
			case 9:
				this.setAll(96, 10, "Trpaslík ");
				break;
			case 10:
				this.setAll(85, 69, "Lev ");
				break;
			default:
				this.setAll(10, 10, "error ");
			}
			switch (r.nextInt(15) + 0) {
			case 1:
				HP -= r.nextInt(20) + 10;
				if (HP >= 1) {
					Jmeno = Jmeno + "zraněný/á ";
				} else {
					HP = 0;
					Jmeno = Jmeno + "mrtvý/á ";
				}
				break;
			case 2:
				DM += r.nextInt(2) + 1;
				DM++;
				Jmeno = Jmeno + "naštvaný/á ";
			}
			break;
		case 4:
		}
	}

	public int getHP() {
		return HP;
	}

	public int getDM() {
		return DM;
	}

	public String getJmeno() {
		return Jmeno;
	}

}
