import java.util.Scanner;
import java.net.*;
import java.io.*;

/*

	ServerDisplay sends all messages to all clients.

*/

public class ServerDisplay extends Thread {

	private Socket server;
	private ChatMessage current_msg;

	public ServerDisplay() {}

	public ServerDisplay(Socket s) {

		this.server = s;

	}

	/*
	
		Sends the message from server to clients. ChatMessage has
		the sender's IP address and message.

	*/

	public void send_message(Socket s, ChatMessage c) {			

		try {

			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(c.sender_ip + ": " + c.message);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		System.out.println("server's message display thread");
	}

}