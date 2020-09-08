
public enum Dan {
	A,B,C;
	public float toFloat() {
		switch(this) {
		case A:
			return 0.1f;
		case B:
			return 0.15f;
		case C:
			return 0.21f;
		default:
			return 0.1f;
		
		}
	}
}
