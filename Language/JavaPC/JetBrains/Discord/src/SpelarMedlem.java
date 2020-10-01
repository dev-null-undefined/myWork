public class SpelarMedlem extends Medlem {
	private String position;
	private boolean isFavorite;

	public SpelarMedlem(String namn, String roll, String position) {
		super(namn, roll);
		this.position = position;
		this.isFavorite=false;
	}
	public boolean isFavorite(){
		return isFavorite;
	}
	public void setFavorite(boolean isFavorite){
		this.isFavorite=isFavorite;
	}
	public String getPosition() {
		return position;
	}
}
