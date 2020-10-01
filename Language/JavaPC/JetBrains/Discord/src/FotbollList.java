import java.util.ArrayList;

public class FotbollList {

	private ArrayList<SpelarMedlem> fotbollList;

	public FotbollList() {
		this.fotbollList = new ArrayList<SpelarMedlem>();
	}

	public boolean addSpelare(SpelarMedlem sm) {
		if (findSpelare(sm.getNamn()) >= 0) {
			System.out.println("Spelaren är redan sparad.");
			return false;
		}
		fotbollList.add(sm);
		return true;
	}

	public boolean removeSpelare(SpelarMedlem sm) {
		int foundPosition = findSpelare(sm);
		if (foundPosition < 0) {
			System.out.println(sm.getNamn() + ", gick inte att hitta.");
			return false;
		}
		this.fotbollList.remove(foundPosition);
		System.out.println(sm.getNamn() + ", har tagits bort.");
		return true;
	}

	private int findSpelare(SpelarMedlem sm) {
		return this.fotbollList.indexOf(sm);
	}

	private int findSpelare(String medlemNamn) {
		for(int i=0; i<this.fotbollList.size(); i++) {
			SpelarMedlem sm = this.fotbollList.get(i);
			if(sm.getNamn().equals(medlemNamn)) {
				return i;
			}
		}
		return -1;
	}
	public SpelarMedlem searchPlayer(String name) {
		int position = findSpelare(name);
		if (position >= 0) {
			return this.fotbollList.get(position);

		}
		return null;
	}
	public void printFotbollList() {
		System.out.println("Såhär många personer är med i laget " + fotbollList.size());
		for (int i = 0; i < this.fotbollList.size(); i++) {
			if (this.fotbollList.get(i).getRoll().equals("Fotbollspelare")) {
				System.out.println((i + 1) + "." +
						this.fotbollList.get(i).getNamn() + "." +
						this.fotbollList.get(i).getRoll() + "." + this.fotbollList.get(i).getPosition());
			}else{
				System.out.println((i + 1) + "." +
						this.fotbollList.get(i).getNamn() + "." +
						this.fotbollList.get(i).getRoll());
			}
		}
	}

	public void printFavoriteFotbollList() {
		for (int i = 0; i < this.fotbollList.size(); i++) {
			if(this.fotbollList.get(i).isFavorite()){
				if (this.fotbollList.get(i).getRoll().equals("Fotbollspelare")) {
					System.out.println((i + 1) + "." +
							this.fotbollList.get(i).getNamn() + "." +
							this.fotbollList.get(i).getRoll() + "." + this.fotbollList.get(i).getPosition());
				}else{
					System.out.println((i + 1) + "." +
							this.fotbollList.get(i).getNamn() + "." +
							this.fotbollList.get(i).getRoll());
				}
			}
		}
	}


}
