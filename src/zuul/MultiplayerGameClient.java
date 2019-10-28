package zuul;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// NOTE: Adapted from https://www.geeksforgeeks.org/socket-programming-in-java/
public class MultiplayerGameClient {
	private Socket socket = null;
	private ArrayList<String> players = new ArrayList<>();

	private void initClientData() {

	}

	private void listenForData() {

	}

	private void updateClient() {

	}

	public void updateServer(JsonObject json, Player player) {

	}

//	@Override
//	public void run() {
//
//	}

	// Adapted from https://www.journaldev.com/741/java-socket-programming-server-client
	MultiplayerGameClient(String address, int port) {
		String jsonString = GameController.getRoomModel().getJsonString();
        InetAddress host = null;
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
		//establish socket connection to server
		try {
			socket = new Socket(host.getHostName(), port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Sending request to Socket Server");
			oos.writeObject(jsonString);
			//read the server response message
			ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			System.out.println("Message: " + message);
			//write to socket using ObjectOutputStream
			//close resources
			ois.close();
			oos.close();
			Thread.sleep(100);
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
