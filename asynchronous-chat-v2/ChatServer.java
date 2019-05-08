import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
	
	private ServerSocket server_socket;
	private ArrayList<ChatMessage> message_dump = new ArrayList<ChatMessage>();
	private ChatMessage current_message;
	private ArrayList<Socket> client_array;

	// public ChatServer(int port) {

	// 	try {

	// 		server_socket = new ServerSocket(port);
	// 		server_socket.setSoTimeout(10000);
	// 		client_array = new ArrayList<Socket>();			// array of sockets for the clients to connect to

	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
		
	// }

	// public void run() {

	// 	// while (true) {

	// 		System.out.println("main ChatServer thread");
	// 		int client_count = 0;

	// 		// while (true) {}

	// 	// }

	// }

	public static void main(String[] args) {

		// run command: java ChatServer <port number>

		int port = Integer.parseInt(args[0]);

		try {

			System.out.println("Chat server up on port " + args[0] + " ...");
			ExecutorService thread_pool = Executors.newFixedThreadPool(5);

			try (ServerSocket listener = new ServerSocket(port)) {

				while (true) {
					ClientHandler new_client;
					thread_pool.execute(new_client = new ClientHandler(listener.accept()));
					System.out.println(new_client.socket.getRemoteSocketAddress() + " has connected to the server.");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}