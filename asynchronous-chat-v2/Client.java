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

		try {

			client = new Socket(server_name, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());

			Scanner incoming = new Scanner(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());

			while (true) {

				Scanner input = new Scanner(System.in);
				System.out.print("Message: ");
				String msg = input.nextLine();

				if (msg.equals("bye")) {
					out.writeUTF(msg);
					break;
				} else {
					out.writeUTF(msg);
				}

				// TODO: find out why it's not receiving anything
				String line = incoming.nextLine();
				System.out.println("incoming" + line);

			}

			client.close();

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

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