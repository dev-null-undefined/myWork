import java.util.Random;

public class PraskHoKokota {
	private static final Random rnd = new Random();

	public static Postava Enemy(int level) {
		Rasa rasa = null;
		if (level <= 5) {
			int random = rnd.nextInt(3);
			switch (random) {
			case 0:
				rasa = new Skritek();
				break;
			case 1:
				rasa = new Gnol();
				break;
			case 2:
				rasa = new Clovek();
				break;
			}

		}
		if (level > 5 && level <= 10) {
			int random = rnd.nextInt(3);
			switch (random) {
			case 0:
				rasa = new Skret();
				break;
			case 1:
				rasa = new Clovek();
				break;
			case 2:
				rasa = new Trpaslik();
				break;
			}

		}
		if (level > 5 && level <= 10) {
			int random = rnd.nextInt(3);
			switch (random) {
			case 0:
				rasa = new Skret();
				break;
			case 1:
				rasa = new Clovek();
				break;
			case 2:
				rasa = new Trpaslik();
				break;
			}
		}
		if (level >= 11 && level <= 15) {
			int random = rnd.nextInt(3);
			switch (random) {
			case 0:
				rasa = new Elf();
				break;
			case 1:
				rasa = new Troll();
				break;
			case 2:
				rasa = new Nemrtvy();
				break;
			}
		}
		if (level >= 16) {
			int random = rnd.nextInt(9);
			switch (random) {
			case 1:
				rasa = new Clovek();
				break;
			case 2:
				rasa = new Elf();
				break;
			case 3:
				rasa = new Trpaslik();
				break;
			case 4:
				rasa = new TemnyElf();
				break;
			case 5:
				rasa = new Troll();
				break;
			case 6:
				rasa = new Nemrtvy();
				break;
			case 7:
				rasa = new Skret();
				break;

			case 8:
				rasa = new Skritek();
				break;
			case 0:
				rasa = new Gnol();
				break;
			}
		}
		int input = 0;
		int sila = 0;
		int inteligence = 0;
		int obratnost = 0;
		int odolnost = 0;
		for (int i = 0; i < level * 3; i++) {
			input = rnd.nextInt(4);
			switch (input) {
			case 0:
				sila++;
				break;
			case 1:
				inteligence++;
				break;
			case 2:
				obratnost++;
				break;
			case 3:
				odolnost++;
				break;

			}
		}
		int random = rnd.nextInt(3);
		Postava enemy = null;
		switch (random) {
		case 0:
			enemy = new Valecnik("Bot", level, sila, inteligence, obratnost, odolnost, 0, rasa);
			break;
		case 1:
			enemy = new Mag("Bot", level, sila, inteligence, obratnost, odolnost, 0, rasa);
			break;
		case 2:
			enemy = new Pruzkumnik("Bot", level, sila, inteligence, obratnost, odolnost, 0, rasa);
			break;
		}
		return enemy;
	}

	public static boolean Boj(Postava hrac, Postava enemy) {
		boolean plTurn = true;
		if (rnd.nextBoolean()) {
			plTurn = true;
		}
		double hit;
		while (hrac.getHp() > 0 && enemy.getHp() > 0) {
			if (plTurn) {
				// HRAC ATTACK
				hit = hrac.getUtok();
				double h1 = hit / (enemy.getObrana(hrac) + 1.0);
				enemy.setHp(enemy.getHp() - h1);
				plTurn = false;
				//System.out.println(h1 + " Ja");
                                System.out.println("Enemy hp:"+enemy.getHp()+"   =:"+h1);

			} else {
				// ENEMY ATTACK
				hit = enemy.getUtok();
				double h2 = hit / (hrac.getObrana(enemy) + 1.0);
				hrac.setHp(hrac.getHp() - h2);
				plTurn = true;
				//System.out.println(h2 + " Nepritel");
                                System.out.println("Ja hp:"+hrac.getHp()+"   =:"+h2);

			}

		}
		if (enemy.getHp() <= 0) {
			hrac.pridejZk(enemy.getLevel() * 10);

			System.out.println("Vyhral");
			return true;
		} else {
			System.out.println("Prohral");  
			return false;

		}
	}

}
