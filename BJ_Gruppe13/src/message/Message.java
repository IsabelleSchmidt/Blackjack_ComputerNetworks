package message;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Message {
	private Commands command;
	private Map<String, String> attributes;
	
	public Message(String line) {
		attributes = new HashMap<>();
		String[] components = line.split(";");
		
		if (components.length >= 1) {
			try {
				command = Commands.valueOf(components[0]);
			} catch (Exception e) {
				command = Commands.ILLEGAL_COMMAND;
			}
		}
		
		if (components.length >= 2) {
			try {
				String attributeLine = components[1];
				for (String pair : attributeLine.split(",")) {
					String[] pairComponents = pair.split("=");
					attributes.put(pairComponents[0], pairComponents[1]);
				}
			} catch (Exception e) {
				command = Commands.ILLEGAL_COMMAND;
			}
		}
	}
	
	public Message(Commands command) {
		this.command = command;
	}
	
	public Message(Commands command, Map<String, String> attributes) {
		this.command = command;
		this.attributes = attributes;
	}
	
	public String serialize() {
		if (attributes != null) {
			return String.format("%s;%s", command, attributes.entrySet().stream()
					.map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
					.collect(Collectors.joining(",")));
		}
		return command.name();
		
	}
	
	public static Message parse(String raw) {
        if (raw == null || raw.isEmpty()) {
            return null;
        }
        
        String[] components = raw.split(";");
        Commands type = Commands.valueOf (components[0]);
        HashMap<String, String> body = new HashMap<>();
        
        if (components.length >= 2) {
            for (String keyValuePair : components[1].split(",")) {
                String[] kvComponents = keyValuePair.split("=");
                body.put(kvComponents[0], kvComponents[1]);
            }
        }
        return new Message(type, body);

    }

	public Commands getCommand() {
		return command;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
}
