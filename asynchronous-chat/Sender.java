import java.util.Scanner;
import java.net.*;
import java.io.*;

/*

	Sender is for clients sending messages to the server.

*/

public class Sender extends Thread {

	private Socket server;
	private ChatMessage current_msg;
	private String message;

	public Sender() {}

	public Sender(Socket s) {

		this.server = s;

	}

/*

	// this function is in ServerDisplay.java now

	public ChatMessage send_message(Socket s, ChatMessage c) {			// sender function for server

		try {

			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(c.sender_ip + ": " + c.message);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

*/

	/*
	
		Client sends a message to the server.

	*/

	public String send_message(Socket c) {

		try {

			OutputStream outToServer = c.getOutputStream();
        	DataOutputStream out = new DataOutputStream(outToServer);
	
			Scanner msg_reader = new Scanner(System.in);
			System.out.print("Message: ");
	
			this.message = msg_reader.nextLine();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;

	}

	public void run() {
		System.out.println("sender thread");
	}

}