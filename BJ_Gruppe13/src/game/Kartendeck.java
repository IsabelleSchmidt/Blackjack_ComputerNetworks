package game;

import java.util.ArrayList;

public class Kartendeck {
	
	ArrayList<Karte> kartendeck;

	public Kartendeck() {
		kartendeck = new ArrayList<>();
		
		for(int i = 2; i <= 10; i++) {
			for(Farben farbe : Farben.values()) {
				kartendeck.add(new Karte(Integer.toString(i), farbe.toString(), i));
			}
		}
		
		for(Farben farbe : Farben.values()) {
			kartendeck.add(new Karte("Bube", farbe.toString(), 10));
		}
		
		for(Farben farbe : Farben.values()) {
			kartendeck.add(new Karte("Dame", farbe.toString(), 10));
		}
		
		for(Farben farbe : Farben.values()) {
			kartendeck.add(new Karte("König", farbe.toString(), 10));
		}
		
		for(Farben farbe : Farben.values()) {
			kartendeck.add(new Karte("Ass", farbe.toString(), 11));
		}
		
	}

	public Karte getKarte() {
		int index = (int) (Math.random() * kartendeck.size());
		Karte karte = kartendeck.get(index);
		kartendeck.remove(index);
		return karte;
	}
	
}
