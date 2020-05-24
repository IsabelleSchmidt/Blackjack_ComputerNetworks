package game;

import java.io.IOException;

import client_server.BlackJackListener;
import client_server.Client;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GameState {
	private int TWENTY_ONE = 21;
	private GameResults gameResult;
	private Kartendeck kartendeck;
	private int dielerScore;
	
	public Client client;
	
	public SimpleStringProperty letzteKarte;
	public SimpleIntegerProperty playerScore;
	public SimpleBooleanProperty running;
	
	
	public GameState() {
		playerScore = new SimpleIntegerProperty();
		playerScore.set(0);
		letzteKarte = new SimpleStringProperty();
		running = new SimpleBooleanProperty();
		running.set(true);
		gameResult = null;
		kartendeck = new Kartendeck();
	}

	public int getPlayerScore() {
		return playerScore.get();
	}

	public int getDielerScore() {
		return dielerScore;
	}
	
	/**
	 * Spieler bekommt eine Karte
	 */
	public Karte getCard() {
		//Karte fuer Spieler
		Karte karte = kartendeck.getKarte();
		
		if (karte.getName().equals("Ass")) {
			if (playerScore.get() + karte.getWert() > TWENTY_ONE) {
				this.playerScore.set(playerScore.get() + 1);
			} else {
				this.playerScore.set(playerScore.get() + karte.getWert());
			}	
		} else {
			this.playerScore.set(playerScore.get() + karte.getWert());
		}
		
		
		//Karte fuer Dealer
		this.dielerScore += kartendeck.getKarte().getWert();
		
		//Pruefe den Stand
		if (playerScore.get() >= TWENTY_ONE) {
			running.set(false);
			
			if (playerScore.get() > TWENTY_ONE) {
				gameResult = GameResults.VERLOREN;
				return karte;
			}
			
			if (playerScore.get() == TWENTY_ONE) {
				if (dielerScore > TWENTY_ONE) {
					gameResult = GameResults.GEWONNEN;
				} else if (dielerScore < TWENTY_ONE) {
					gameResult = GameResults.GEWONNEN;
				} else if (dielerScore == TWENTY_ONE) {
					gameResult = GameResults.UNENTSCHIEDEN;
				}
				return karte;
			}
		}
		
		if (dielerScore >= TWENTY_ONE) {
			running.set(false);
			
			if (dielerScore > TWENTY_ONE) {
				if (playerScore.get() == TWENTY_ONE) {
					gameResult = GameResults.GEWONNEN;
				} else if (playerScore.get() < TWENTY_ONE) {
					gameResult = GameResults.GEWONNEN;
				} else if (playerScore.get() > TWENTY_ONE) {
					gameResult = GameResults.VERLOREN;
				}
				return karte;
			}
			
			if (dielerScore == TWENTY_ONE) {
				if (playerScore.get() > TWENTY_ONE) {
					gameResult = GameResults.VERLOREN;
				} else if (playerScore.get() < TWENTY_ONE) {
					gameResult = GameResults.VERLOREN;
				} else if (playerScore.get() == TWENTY_ONE) {
					gameResult = GameResults.UNENTSCHIEDEN;
				}
				return karte;
			}
			
		}
		
		return karte;
	}
	
	/**
	 * Spieler fordert auf, dass die Karten geoeffnet werden. Spiel wird beendet
	 */
	public void openCards() {
		running.set(false);
		
		if (playerScore.get() > dielerScore) {
			gameResult = GameResults.GEWONNEN;
		} else if (playerScore.get() < dielerScore) {
			gameResult = GameResults.VERLOREN;
		} else if (playerScore.get() == dielerScore) {
			gameResult = GameResults.UNENTSCHIEDEN;
		}
		
	}
	
	public boolean isRunning() {
		return this.running.get();
	}
	
	public void stopGame() {
		this.running.set(false);
	}

	public GameResults getGameResult() {
		return gameResult;
	}

	public void setGameService(Client client) {
		this.client = client;
		
		this.client.setListener(new BlackJackListener() {
			 
			@Override
			public void cardPosted(String karte, int pScore) {
				letzteKarte.set(karte);
				playerScore.set(pScore);
				System.out.println("Client: vom Server bekommen:" + karte);
			}


			@Override
			public void gameFinished(GameResults result, int dScore) {
				gameResult = result;
				dielerScore = dScore;
				running.set(false);
				try {
					System.out.println("Game: Schließe Verbindung zum Server...");
					client.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


			@Override
			public void gameFinished(GameResults result, int pScore, int dScore, String karte) {
				gameResult = result;
				dielerScore = dScore;
				playerScore.set(pScore);
				letzteKarte.set(karte);
				running.set(false);
				try {
					System.out.println("Game: Schließe Verbindung zum Server...");
					client.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
            
        });
	}
	
}
