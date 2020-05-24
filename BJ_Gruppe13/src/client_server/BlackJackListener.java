package client_server;

import game.GameResults;

public interface BlackJackListener {
	
    void cardPosted(String karte, int playerScore);
    void gameFinished(GameResults gameResult, int dielerScore);
    void gameFinished(GameResults gameResult, int playerScore, int dielerScore, String karte);

}
