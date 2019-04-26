import java.util.Scanner;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;
	private ArrayList<ChatMessage> message_dump = new ArrayList<ChatMessage>();
	private ChatMessage current_message;
	private  ArrayList<Client> client_array;
	public ChatServer() {

		try {

			server_socket = new ServerSocket(12345);		// default port
			server_socket.setSoTimeout(10000);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ChatServer(int port) {

		try {

			server_socket = new ServerSocket(port);
			server_socket.setSoTimeout(10000);
			 client_array = new ArrayList<Client>();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void run() {

		// while (true) {

			System.out.println("main ChatServer thread");
			// ServerDisplay msg_display = new ServerDisplay();
			// msg_display.start();
			// Receiver receiver = new Receiver();
			// receiver.start();

			while (true) {
				try {
					
					ServerDisplay msg_display = new ServerDisplay();
					msg_display.start();
					Receiver receiver = new Receiver();
					receiver.start();
						
					Socket chat_server = server_socket.accept();
					System.out.println("Just connected to " + chat_server.getRemoteSocketAddress());
						Client new_client = new Client(chat_server);
						//add this client to arraylist
						client_array.add(new_client);
				
						new_client.start();


					// String sender_addr = chat_server.getRemoteSocketAddress().toString();
	
					// DataInputStream in = new DataInputStream(chat_server.getInputStream());
					// String sender_message = in.readUTF();
	
					// DataOutputStream out = new DataOutputStream(chat_server.getOutputStream());
					
					// this.current_message = new ChatMessage(sender_addr, sender_message);
					while (true) {
						this.current_message = receiver.receive_message(chat_server);
						this.message_dump.add(this.current_message);
		
						// out.writeUTF(this.current_message.sender_ip + ": " + this.current_message.message);
						msg_display.send_message(chat_server, this.current_message);
					}
	
				} catch (SocketTimeoutException s) {
	
					System.out.println("Connection timed out!");
					// break;
	
				} catch (IOException e) {
					e.printStackTrace();
					// break;
				}

			}

		// }

	}

	public static void main(String[] args) {

		// run command: java ChatServer <port number>

		int port = Integer.parseInt(args[0]);

		try {

			ChatServer chat_server = new ChatServer(port);
			chat_server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
