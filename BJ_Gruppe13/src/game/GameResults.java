package game;

public enum GameResults {
	VERLOREN("verloren"), GEWONNEN("gewonnen"), UNENTSCHIEDEN("unentschieden");
	
	private String name;
	
	private GameResults(String name) {
		this.name = name;
	}
}
