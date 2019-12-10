import java.util.Random;

public class NPC {
	public static String anime() {
		Random r = new Random();
		String retur;
		int hp, dm;
		switch (r.nextInt(10) + 0) {
		case 1:
			retur = "Divočák ";
			hp = 7;
			dm = 5;
			break;
		case 2:
			retur = "Kočka ";
			hp = 9;
			dm = 3;
			break;
		case 3:
			retur = "Vlk ";
			hp = 5;
			dm = 6;
			break;
		case 4:
			retur = "Opička ";
			hp = 4;
			dm = 2;
			break;
		case 5:
			retur = "Hroch ";
			hp = 9;
			dm = 4;
			break;
		case 0:
			retur = "Pavouk ";
			hp = 1;
			dm = 9;
			break;
		case 6:
			retur = "Orel ";
			hp = 5;
			dm = 2;
			break;
		case 7:
			retur = "Tygr ";
			hp = 7;
			dm = 9;
			break;
		case 8:
			retur = "MEDĚD ";
			hp = 8;
			dm = 7;
			break;
		case 9:
			retur = "Had ";
			hp = 3;
			dm = 3;
			break;
		case 10:
			retur = "Ovce ";
			hp = 4;
			dm = 1;
			break;
		default:
			retur = "error ";
			hp = 1;
			dm = 1;
		}
		switch (r.nextInt(15) + 0) {
		case 1:
			hp -= r.nextInt(2) + 1;
			if (hp == 10) {
				return retur + 9 + dm;
			} else {
				if (hp >= 1) {
					return "Zraněný " + retur + hp + dm;
				} else {
					return "Mrtvý  " + retur + hp + dm;
				}
			}
		case 2:
			dm += r.nextInt(2) + 1;
			if (dm > 9) {
				return "Naštvaný " + retur + hp + 9;
			} else {
				return "Naštvaný " + retur + hp + dm;
			}
		default:
			return retur + hp + dm;
		}
	}
}
