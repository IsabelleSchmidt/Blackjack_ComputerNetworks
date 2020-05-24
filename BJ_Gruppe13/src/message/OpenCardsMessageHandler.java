package message;

import game.GameState;

public class OpenCardsMessageHandler implements MessageHandler {

	public OpenCardsMessageHandler() {
		
	}

	@Override
	public Message handle(Message m, GameState game) {
		if (game.getGameResult() != null) {
			return MessageGenerator.illegalCommand();
		}
		
		game.openCards();
		return MessageGenerator.gameFinished(
					game.getGameResult().name(),
					Integer.toString(game.getDielerScore()));
	}

}
