import java.util.Random;

public class Items {
	private int brneniPrice, brneni, damagePrice, damage, maxHpPrice, maxHp, hp, hpPrice, kouzloId, kouzloPrice,
			brneniMaxHpPrice, brneniMaxHp, MaxHpBrneni;
    private String brneni1,damage1,maxHp1,hp1,kouzlo,brneniMaxHp1;
	public Items(boolean kouzla, int poloha) {
		Random r = new Random();
		switch (poloha) {
		case 1:
			switch (r.nextInt()) {//brneni
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			}
			switch (r.nextInt()) {//damage
			case 1:
				
				break;
			case 2:
				break;
			case 3:
				break;
			}
			switch (r.nextInt()) {//maxHp
			case 1:
				
				break;
			case 2:
				break;
			case 3:
				break;
			}
			switch (r.nextInt()) {//hp
			case 1:
				
				break;
			case 2:
				break;
			case 3:
				break;
			}
			switch (r.nextInt()) {//kouzlo
			case 1:
				kouzloId=1;
				kouzloPrice=100;
				kouzlo="double damage";
				break;
			case 2:
				break;
			case 3:
				break;
			}
			switch (r.nextInt()) {//brneniMaxHp
			case 1:
				
				break;
			case 2:
				break;
			case 3:
				break;
			}
		case 2:
		case 3:
		case 4:
		}

	}

}
