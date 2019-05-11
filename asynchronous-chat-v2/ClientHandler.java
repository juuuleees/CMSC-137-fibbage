import java.util.Scanner;
import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {

	public Socket socket;
	private Scanner msg_scanner;
	private String message;

	public ClientHandler(Socket s) {
		this.socket = s;
	}

	public void run() {

		try {

			DataInputStream in = new DataInputStream(socket.getInputStream());
			PrintStream out = new PrintStream(socket.getOutputStream());

			while (true) {

				// receive message from client
				String input = in.readUTF();
				System.out.println("got it");

				out.println(input);

			}


		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}
	
}

/*

	TO DO:

		> server has to send message to the rest of the clients
			- add the client sockets to ArrayList<Socket> then 
				send to each

*/