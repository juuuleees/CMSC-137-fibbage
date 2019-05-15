import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class ChatServer extends Thread {
	
	// private ServerSocket server_socket;
	private static ArrayList<ClientHandler> client_array = new ArrayList<ClientHandler>();
	private static ArrayList<OutputStream> writers = new ArrayList<OutputStream>();
	private static String current_message;

	public static void main(String[] args) {

		// run command: java ChatServer <port number>
		// ArrayList<ClientHandler> client_array = new ArrayList<ClientHandler>();

		int port = Integer.parseInt(args[0]);

		try {

			System.out.println("Chat server up on port " + args[0] + " ...");
			ExecutorService thread_pool = Executors.newFixedThreadPool(5);

			try (ServerSocket listener = new ServerSocket(port)) {

				while (true) {

					if (client_array.size() <= 8) {

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

			// Quiz fibbage = new Quiz();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static class ClientHandler implements Runnable {

		private Socket socket;
		private String message;
		private PrintWriter writer;
	
		public ClientHandler(Socket s) {
			this.socket = s;
		}
	
		public void run() {
	
			try {
	
				DataInputStream in = new DataInputStream(socket.getInputStream());
				// writer = new PrintWriter(socket.getOutputStream(), true);
				OutputStream out = socket.getOutputStream();
				// writers.add(writer);
				writers.add(out);
	
				while (true) {
	
					// receive message from client
					current_message = in.readUTF();
					current_message = socket.getRemoteSocketAddress() + ": " + current_message;
					System.out.println(current_message);

					if (current_message.equals("bye")) {
						System.out.println(socket.getRemoteSocketAddress() + " has left the chat.");
						client_array.remove(this);
						System.out.println("Current users online: " + client_array.size());
						socket.close();
					}

					// send messages to other clients
					// TODO: find out why clients aren't receiving the message
					synchronized (writers) {

						for (OutputStream os : writers) {
							
							PrintWriter sender = new PrintWriter(os, true);

							sender.println(current_message);
							sender.flush();

						}
					}
	
				}
	
	
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
	
		}
		
	}

}

/*

	References:

		https://cs.lmu.edu/~ray/notes/javanetexamples/?fbclid=IwAR2-S2Cj5_b6ts-v753vPGrW4piaWbLeUt1hC7ZqogLdrC0-Z511K967OCE#chat

*/