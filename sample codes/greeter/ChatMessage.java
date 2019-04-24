import java.net.*;
import java.io.*;

/*
	
	Save the message details in one object.
	
*/

public class ChatMessage {

	protected String sender_ip;
	protected String message;

	// constructors
	public ChatMessage() {}

	public ChatMessage(String message) {
		this.message = message;
	}

	public ChatMessage(String sender, String msg) {
		this.sender_ip = sender;
		this.message = msg;
	}

	// getters and setters
	public String get_sender_ip() { return this.sender_ip; }
	public String get_message() { return this.message; }

	public void set_sender_ip(String s) { this.sender_ip = s; }
	public void set_message(String m) { this.message = m; }

}