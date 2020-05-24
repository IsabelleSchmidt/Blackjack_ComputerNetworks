package game;

public class Karte {
	private int wert;
	private String name;
	private String farbe;

	public Karte (String name, String farbe, int wert) {
		this.name = name;
		this.wert = wert;
		this.farbe = farbe;
	}

	public int getWert() {
		return this.wert;
	}

	public String toString() {
		return name + " " + farbe;
	}

	public String getName() {
		return name;
	}

}
