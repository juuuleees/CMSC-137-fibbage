import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {

		// ask user for port and server name
		// if they don't want to provide one use the default port 12345

		Server server = new Server();
		server.start();

		// Client client1 = new Client();
		// client1.start();

		// Client client2 = new Client();
		// client2.start();

	}

}