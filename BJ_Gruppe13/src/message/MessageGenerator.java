package message;

import java.util.HashMap;
import java.util.Map;

public class MessageGenerator {

	public static Message postCard(String karte, String playerScore) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("karte", karte);
		responseMap.put("playerScore", playerScore);
		
		return new Message(Commands.POST_CARD, responseMap);
	}
	
	public static Message gameFinished(String gameResult, String dielerScore) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("gameResult", gameResult);
		responseMap.put("dielerScore", dielerScore);
		
		return new Message(Commands.GAME_FINISHED, responseMap);
	}
	
	public static Message overTwentyOne(String karte, String gameResult, String playerScore, String dielerScore) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("gameResult", gameResult);
		responseMap.put("playerScore", playerScore);
		responseMap.put("dielerScore", dielerScore);
		responseMap.put("karte", karte);
	
		return new Message(Commands.GAME_FINISHED, responseMap);
	}
	
	public static Message illegalCommand() {
		return new Message(Commands.ILLEGAL_COMMAND);
	}

}
