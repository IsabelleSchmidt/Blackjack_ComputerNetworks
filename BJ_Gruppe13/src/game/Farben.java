package game;

public enum Farben {
	HERZ("Herz"), KARO("Karo"), PIK("Pik"), KREUZ("Kreuz");
	
	private String name;
	
	private Farben(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
