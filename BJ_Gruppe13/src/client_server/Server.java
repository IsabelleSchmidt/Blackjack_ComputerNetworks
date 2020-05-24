package client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import game.GameState;
import message.Message;
import message.Commands;
import message.GetCardMassageHandler;
import message.MessageHandler;
import message.OpenCardsMessageHandler;

public class Server {
	private int port;
	private Map<Commands, MessageHandler> handlerMap;
	private boolean interrupted;
	ServerSocket serverSocket;
	
	public Server(int port) {
		this.port = port;
		this.handlerMap = new HashMap<>();
		try {
			serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server: Warte auf Client...");
	}
	
	public void registerHandler(Commands command, MessageHandler handler) {
		handlerMap.put(command, handler);
	}
	
	public void start() {
			
			while (interrupted == false) {
				try {
					Socket socket = serverSocket.accept();
					System.out.println("Server: Client hat sich verbunden: " + socket.getRemoteSocketAddress() + ".");
					
					GameState gameState = new GameState();
					
					final Thread tread = new Thread(new Runnable() {

						@Override
						public void run() {
							
							final BufferedReader reader;
							final BufferedWriter writer;
							
							try {
								reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
								writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
								
								while(gameState.isRunning()) {
									handleRequest(socket, gameState, reader, writer);
								}
							
							} catch (IOException e) {
								if (interrupted) {
									System.out.println("Server Socket wurde geschlossen.");
								}
								e.printStackTrace();
							}
							
						}
					});
					tread.start();
					
				} catch (IOException e) {
					
				}
			}
	}
	
	public void stop() {
        if (interrupted == false) {
            interrupted = true;
            try {
            	System.out.println("Schlieﬂe Server Socket...");
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	private void handleRequest(final Socket socket, GameState gameState, final BufferedReader reader, final BufferedWriter writer) {
		
		while (true) {
			final String line;
			
			try {
				line = reader.readLine();
				if (line == null) {
					break;
				}
				
				Message message = new Message(line);
				System.out.printf("Server: vom Client (%s) empfangen: %s%n", socket.getRemoteSocketAddress(), line);
				
				MessageHandler handler = handlerMap.get(message.getCommand());
				
				if (handler != null) {
					Message response = handler.handle(message, gameState);
					if (response != null) {
						System.out.println("Server: " + response.serialize());
						writer.write(response.serialize() + "\n");
						writer.flush();
					}
				}
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(28888);
		server.registerHandler(Commands.GET_CARD, new GetCardMassageHandler());
		server.registerHandler(Commands.OPEN_CARDS, new OpenCardsMessageHandler());
		server.start();
	}
}
