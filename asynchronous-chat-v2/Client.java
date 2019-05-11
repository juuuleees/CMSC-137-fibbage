import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client extends Thread {
	
	private Socket client;
	private String server_name;
	private int port;
	private boolean transmitting = false;

	public Client() {}

	public Client(String s_name, int port) {

		this.server_name = s_name;
		this.port = port;

	}

	// set defaults
	public void set_default_server(String s) { this.server_name = s; }
	public void set_default_port(int p) { this.port = p; }
	public void set_transmitting(boolean t) { this.transmitting = t; }

	public void run() {
		System.out.println("client thread");

		try {

			client = new Socket(server_name, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());

			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());

			while (true) {

				Scanner input = new Scanner(System.in);
				System.out.print("Message: ");
				String msg = input.nextLine();

				if (msg.equals("bye")) {
					break;
				} else {
					out.writeUTF(msg);
				}

				// TODO: find out why it's not receiving anything
				in.readUTF();

			}

			client.close();

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		// Sender client_sender = new Sender();
		// Receiver client_receiver = new Receiver();

		// client_sender.start();
		// client_receiver.start();

		// // while (true) {

		// 	try {

		// 		this.client = new Socket(this.server_name, this.port);
		// 		System.out.println("Just connected to " + client.getRemoteSocketAddress());
				
		// 		while (true) {
		// 			client_sender.send_message(client);
		// 		}
	
		// 	} catch (IOException e) {
		// 		e.printStackTrace();
		// 		// break;
		// 	}

		// // }


	}

	public static void main(String[] args) {

		// run command: java Client <server ip> <port number> 

		String server = args[0];
		int port = Integer.parseInt(args[1]);

		try {

			Client client = new Client(server, port);
			client.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}