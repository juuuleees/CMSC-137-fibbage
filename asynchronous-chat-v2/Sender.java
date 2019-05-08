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
	private boolean sent_message = false;

	public Sender() {}

	public Sender(Socket s) {

		this.server = s;

	}

	public void set_sent_message(boolean s) { this.sent_message = s; }

	/*
	
		Client sends a message to the server.

	*/

	public String send_message(Socket c) {

		// System.out.println("send halp");

		try {

			OutputStream outToServer = c.getOutputStream();
        	DataOutputStream out = new DataOutputStream(outToServer);
	
			Scanner msg_reader = new Scanner(System.in);
			System.out.print("Message: ");
	
			this.message = msg_reader.nextLine();
			System.out.println(this.message);

			out.writeUTF(this.message);

			set_sent_message(true);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// System.out.println("not sending!!");
		}

		return message;

	}

	public void run() {
		System.out.println("sender thread");
		// this.send_message(this.server);
	}

}