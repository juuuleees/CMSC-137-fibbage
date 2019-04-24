import java.util.Scanner;
import java.net.*;
import java.io.*;

/*

	Receiver accepts messages going from client to server.

*/

public class Receiver extends Thread {

	private Socket server;
	private ChatMessage new_msg;

	// constructors
	public Receiver() {}

	public Receiver(Socket s) {
		this.server = s;
	}

	public ChatMessage receive_message(Socket s) {		

		// System.out.println("halp");

		try {

			String sender_addr = s.getRemoteSocketAddress().toString();

			DataInputStream in = new DataInputStream(s.getInputStream());
			String sender_message = in.readUTF();

			System.out.println(sender_message);
	
			new_msg = new ChatMessage(sender_addr, sender_message);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("receiving!!");
		}

		return new_msg;

	}

	public void run() {
		System.out.println("receiver thread");
		// this.receive_message(this.server);
	}

}