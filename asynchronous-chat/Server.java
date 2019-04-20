import java.util.Scanner;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Server extends Thread {
	
	private ServerSocket server_socket;
	private ArrayList<ChatMessage> message_dump = new ArrayList<ChatMessage>();
	private ChatMessage current_message;

	public Server() {

		try {

			server_socket = new ServerSocket(12345);		// default port
			server_socket.setSoTimeout(10000);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Server(int port) {

		try {

			server_socket = new ServerSocket(port);
			server_socket.setSoTimeout(10000);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {

		while (true) {

			System.out.println("main server thread");
			ServerDisplay msg_display = new ServerDisplay();
			msg_display.start();
			Receiver receiver = new Receiver();
			receiver.start();

			try {

				Socket server = server_socket.accept();
				// String sender_addr = server.getRemoteSocketAddress().toString();

				// DataInputStream in = new DataInputStream(server.getInputStream());
				// String sender_message = in.readUTF();

				// DataOutputStream out = new DataOutputStream(server.getOutputStream());
				
				// this.current_message = new ChatMessage(sender_addr, sender_message);
				this.current_message = receiver.receive_message(server);
				this.message_dump.add(this.current_message);

				// out.writeUTF(this.current_message.sender_ip + ": " + this.current_message.message);
				msg_display.send_message(server, this.current_message);

			} catch (SocketTimeoutException s) {

				System.out.println("Connection timed out!");
				break;

			} catch (IOException e) {
				e.printStackTrace();
				break;
			}


		}

	}

	public static void main(String[] args) {

		// run command: java Server <port number>

		int port = Integer.parseInt(args[0]);

		try {

			Server server = new Server(port);
			server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}