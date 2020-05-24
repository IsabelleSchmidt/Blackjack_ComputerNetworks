package client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import game.GameResults;
import message.Commands;
import message.Message;

/**
 * 
 * Klasse repraesentiert einen Client. Client erstellt eine Verbindung zum Server, bearbeitet und antwortet 
 * auf die empfangenen Server-Responses.
 *
 */
public class Client {

	private String remoteHost;
    private int remotePort;
    private BlackJackListener listener;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Thread listenThread;
	
    public Client (String remoteHost, int remotePort) {
    	this.remoteHost = remoteHost;
    	this.remotePort = remotePort;
	}
	    
    public void initialize() throws IOException {
        try {
			socket = new Socket(remoteHost, remotePort);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        listen();
		} catch (UnknownHostException e) {
			System.out.println("Client: Server is not available...");
			throw new IOException();
		} catch (IOException e) {
			System.out.println("Client: Server is not available...");
			throw new IOException();
		}
        
    }
    
    /**
     * Empfang von Responses.
     */
    public void listen() {
        
    	if (listenThread != null) {
            listenThread.interrupt();
            listenThread = null;
        }
    	
        listenThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    final String raw = reader.readLine();
                    if (Thread.interrupted()) {
                        break;
                    }
                    if (raw == null) {
                        break;
                    }
                    handleMessage(raw);
                    
                } catch (SocketException e) {
                    if (Thread.interrupted()) {
                        System.out.println("Thread endet wie gewünscht.");
                    } else {
                    	System.out.println("Client: Server is not available...");
                    }
                    break;
                } catch (IOException e) {
                	System.out.println("Client: Server is not available...");
                    break;
                }
            }
        });
        
        listenThread.start();
    }
    
    /**
     * 
     * Methode schließt die Verbindung zum Server und stellt den Empfang von Responses ein.
     * 
     */
    public void shutdown() throws IOException {
        if(listenThread != null) {
            listenThread.interrupt();
            System.out.println("Client: Schließe Socket...");
            socket.close();
            listenThread = null;
        }
    }
    
    /**
     * 
     * Methode bearbeitet den Response und übermittelt die empfangenen Daten an GameState.
     * 
     */
    private void handleMessage(String raw) {
    	
        Message message = Message.parse(raw);
        String gameResult;
    	int playerScore;
    	String karte;
    	
        if (listener != null) {
            switch(message.getCommand()) {
            case POST_CARD:
            	karte = message.getAttributes().get("karte");
            	playerScore = Integer.parseInt(message.getAttributes().get("playerScore"));
                listener.cardPosted(karte, playerScore);
                break;
            
            case GAME_FINISHED:
                if (message.getAttributes().size() == 2) {
                	gameResult = message.getAttributes().get("gameResult");
                	int dielerScore = Integer.parseInt(message.getAttributes().get("dielerScore"));
                	listener.gameFinished(GameResults.valueOf(gameResult), dielerScore);
                
                } else {
                	gameResult = message.getAttributes().get("gameResult");
                	playerScore = Integer.parseInt(message.getAttributes().get("playerScore"));
                	int dielerScore = Integer.parseInt(message.getAttributes().get("dielerScore"));
                	karte = message.getAttributes().get("karte");
                	listener.gameFinished(GameResults.valueOf(gameResult), playerScore, dielerScore, karte);
                }
            	
                break;
                
			default:
				break;
            }
        }
	}
    
    /**
     * 
     * Schreibe ein Message an den Server.
     * @throws IOException 
     */
    private void writeMessage(String raw) throws IOException {
    	System.out.println("Client: " + raw);
    	try {
	        writer.write(raw);
	        writer.flush();
    	} catch (IOException e) {
    		System.out.println("Client: Server is not available...");
    		try {
				shutdown();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		throw new IOException();
    	}
    }
    
    public void setListener(BlackJackListener listener) {
        this.listener = listener;
    }
    
    /**
     * 
     * Erstelle GET_CARD Message.
     */
    public void getCard() throws IOException {
    	String message = Commands.GET_CARD.name();
    	writeMessage(message + "\n");
    }
    
    /**
     * 
     * Erstelle OPEN_CARDS Message.
     */
    public void openCards() throws IOException {
    	String message = Commands.OPEN_CARDS.name();
    	writeMessage(message + "\n");
    }

}
