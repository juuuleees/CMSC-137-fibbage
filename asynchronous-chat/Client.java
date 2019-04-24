import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client extends Thread {
	
	private String server_name;
	private int port;

	public Client() {

		set_default_server();
		set_default_port();

	}

	public Client(String s_name, int port) {

		this.server_name = s_name;
		this.port = port;

	}

	// set defaults
	public void set_default_server() { this.server_name = "Server"; }
	public void set_default_port() { this.port = 12345; }

	public void run() {
		System.out.println("client thread");
		
		Sender client_sender = new Sender();
		Receiver client_receiver = new Receiver();

		client_sender.start();
		client_receiver.start();

		while (true) {

			try {

				Socket client = new Socket(this.server_name, this.port);
				System.out.println("Just connected to " + client.getRemoteSocketAddress());
	
				client_sender.send_message(client);
	
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

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