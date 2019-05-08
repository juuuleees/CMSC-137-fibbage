import java.util.Scanner;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;
	private ArrayList<ChatMessage> message_dump = new ArrayList<ChatMessage>();
	private ChatMessage current_message;
	private ArrayList<Socket> client_array;

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
			client_array = new ArrayList<Socket>();			// array of sockets for the clients to connect to

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void run() {

		// while (true) {

			System.out.println("main ChatServer thread");
			int client_count = 0;

			while (true) {
				try {
					
					ServerDisplay msg_display = new ServerDisplay();
					msg_display.start();
					Receiver receiver = new Receiver();
						
					// Socket chat_server = server_socket.accept();
					// System.out.println("Is connected to " + chat_server.getRemoteSocketAddress() + ": " + chat_server.isConnected());
					// System.out.println("server_socket is connected to " + server_socket.getLocalSocketAddress() + "?: " + server_socket.isBound());

					if (client_count <= 4) {

						Socket client_socket = server_socket.accept();
						System.out.println("Is connected to " + client_socket.getRemoteSocketAddress() + ": " + client_socket.isConnected());
						receiver.start();
						client_array.add(client_socket);

					} else { System.out.println("Server at full capacity."); }

					// Client new_client = new Client(chat_server);
					// //add this client to arraylist
					// client_array.add(new_client);				
					// new_client.start();

					// while (true) {
					// 	this.current_message = receiver.receive_message(chat_server);
					// 	this.message_dump.add(this.current_message);
		
					// 	// out.writeUTF(this.current_message.sender_ip + ": " + this.current_message.message);
					// 	msg_display.send_message(chat_server, this.current_message);
					// }

					for (Socket client : client_array) {

						// if (client.received_message == true) {

							this.current_message = receiver.receive_message(client);
							this.message_dump.add(this.current_message);			
							// out.writeUTF(this.current_message.sender_ip + ": " + this.current_message.message);
							msg_display.send_message(client, this.current_message);

						// }

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
