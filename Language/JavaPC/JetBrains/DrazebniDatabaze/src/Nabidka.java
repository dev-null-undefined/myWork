public record Nabidka(Uzivatel prihazujici,float castka) {
	public Nabidka{
		if (prihazujici == null) {
			throw new IllegalArgumentException("Prihazuji nemuze byt null");
		}
		if (castka < 0) {
			throw new IllegalArgumentException("Caska nemuze byt zaporna");
		}
	}
}
