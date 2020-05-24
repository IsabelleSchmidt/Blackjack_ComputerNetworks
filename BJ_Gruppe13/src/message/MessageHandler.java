package message;

import game.GameState;

public interface MessageHandler {
	Message handle(Message m, GameState game);
}
