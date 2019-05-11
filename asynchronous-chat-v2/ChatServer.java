import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
	
	// private ServerSocket server_socket;
	private static ArrayList<ClientHandler> client_array = new ArrayList<ClientHandler>();

	public static void main(String[] args) {

		// run command: java ChatServer <port number>
		// ArrayList<ClientHandler> client_array = new ArrayList<ClientHandler>();

		int port = Integer.parseInt(args[0]);

		try {

			System.out.println("Chat server up on port " + args[0] + " ...");
			ExecutorService thread_pool = Executors.newFixedThreadPool(5);

			try (ServerSocket listener = new ServerSocket(port)) {

				while (true) {

					if (client_array.size() <= 2) {

						ClientHandler new_client;
						thread_pool.execute(new_client = new ClientHandler(listener.accept()));
						client_array.add(new_client);
	
						System.out.println(new_client.socket.getRemoteSocketAddress() + " has connected to the server.");
						System.out.println("Current users online: " + client_array.size());

					} else {
						System.out.println("Maximum chat capacity reached.");
						continue;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static class ClientHandler implements Runnable {

		private Socket socket;
		private String message;
	
		public ClientHandler(Socket s) {
			this.socket = s;
		}
	
		public void run() {
	
			try {
	
				DataInputStream in = new DataInputStream(socket.getInputStream());
				// PrintStream out = new PrintStream(socket.getOutputStream());
	
				while (true) {
	
					// receive message from client
					message = in.readUTF();
					System.out.println(message);

					// send messages to other clients
					// TODO: find out why clients aren't receiving the message
					synchronized (client_array) {
						for (ClientHandler c : client_array) {

							if (!c.socket.getRemoteSocketAddress().toString().equals(socket.getRemoteSocketAddress().toString())) {
								System.out.println("sending parang awa");
								DataOutputStream out = new DataOutputStream(c.socket.getOutputStream());
								out.writeUTF(message);
								System.out.println("sent!");
							}

						}
					}
	
				}
	
	
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
	
		}
		
	}

}