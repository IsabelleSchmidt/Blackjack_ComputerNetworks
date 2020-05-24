package message;

import game.GameState;
import game.Karte;

public class GetCardMassageHandler implements MessageHandler {

	public GetCardMassageHandler() {
		
	}

	@Override
	public Message handle(Message m, GameState game) {
		if (game.getGameResult() != null) {
			return MessageGenerator.illegalCommand();
		}
		
		Karte karte = game.getCard();
		
		if (game.isRunning()) {
			return MessageGenerator.postCard(karte.toString(), Integer.toString(game.getPlayerScore()));
		}
		
		return MessageGenerator.overTwentyOne(
				karte.toString(),
				game.getGameResult().name(),
				Integer.toString(game.getPlayerScore()),
				Integer.toString(game.getDielerScore())
				);
	}

}
