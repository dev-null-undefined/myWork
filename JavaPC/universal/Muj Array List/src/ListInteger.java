
public class ListInteger {
	private Integer cislo;
	private ListInteger dalsi;

	public ListInteger() {
	}

	private ListInteger(int cislo) {
		this.cislo = cislo;
	}

	public void add(int cislo) {
		if (this.cislo != null) {
			if (dalsi != null) {
				dalsi.add(cislo);
			} else {
				dalsi = new ListInteger(cislo);
			}
		} else {
			this.cislo = cislo;
		}
	}

	public void remove(int index) {
		if (index > 0) {
			if (index == 1) {
				if (dalsi.dalsi == null) {
					dalsi = null;
					return;
				}
			}
			dalsi.remove(index - 1);
			return;
		}
		if (index == 0) {
			if (dalsi != null) {
				cislo = dalsi.get();
				dalsi = dalsi.getDalsi();
			}
		}
	}

	public void clear() {
		cislo = null;
		dalsi = null;
	}

	public String toString() {
		return this.toString("", 0);

	}

	private String toString(String tostring, int index) {
		if (dalsi != null) {
			tostring += index + " " + cislo + "\n";
			return dalsi.toString(tostring, index + 1);
		} else {
			return tostring + index + " " + cislo;
		}

	}

	public int hledej(int co) {
		return this.hledej(0, co);
	}

	private int hledej(int index, int co) {
		if (cislo == co) {
			return index;
		}
		return dalsi.hledej(index + 1, co);
	}

	@SuppressWarnings("unused")
	private void add(ListInteger a) {
		dalsi = a;
	}

	public Integer get(int index) {
		Integer i = null;
		if (index > 0) {
			i = dalsi.get(index - 1);
			return i;
		}
		return cislo;
	}

	public int size() {
		if (cislo != null) {
			int i;
			if (dalsi != null) {
				i = dalsi.size();
				return i + 1;
			}
			return 1;
		} else {
			return 0;
		}
	}

	public void set(int index, int cislo) {
		if (index > 0) {
			dalsi.set(index - 1, cislo);
			return;
		}
		if (index == 0) {
			this.cislo = cislo;
		}
	}

	public void add(int index, int cislo) {
		if (dalsi != null) {
			if (index == 0) {
				ListInteger pomoc = dalsi;
				this.dalsi = new ListInteger(this.cislo);
				this.dalsi.add(pomoc);
				this.cislo = cislo;
				return;
			} else {
				dalsi.add(index - 1, cislo);
			}
		} else {
			dalsi = new ListInteger(cislo);
			return;
		}

	}

	public void serad() {
		for (int x = this.size(); x != 0; x--) {
			this.serat();
		}
	}

	private void serat() {
		if (dalsi != null) {
			if (this.cislo > dalsi.get()) {
               int pomoc=cislo;
               cislo=dalsi.get();
               dalsi.set(pomoc);
               dalsi.serat();
			}else {
				dalsi.serat();
			}
		}else {
			return;
		}

	}
	private void set(Integer cislo) {
		this.cislo=cislo;
	}

	@SuppressWarnings("unused")
	private Integer get() {
		return cislo;
	}

	private ListInteger getDalsi() {
		return dalsi;
	}

}
