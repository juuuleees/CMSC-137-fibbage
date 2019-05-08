import java.util.Scanner;
import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {

	public Socket socket;
	private Scanner msg_scanner;
	private String message;

	public ClientHandler(Socket s) {
		this.socket = s;
	}

	public void run() {

		try {

			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			while (true) {

				// in = new DataInputStream(new InputStream(socket.getInputStream()));
				// out = new DataOutputStream(new OutputStream(socket.getOutputStream()));

				// receive message from server
				// String input = in.readUTF();
				System.out.println(in.readUTF());

				// send message to server
				// msg_scanner = new Scanner(System.in);
				// System.out.print("Message clienthandler:" );

				// this.message = msg_scanner.nextLine();
				// out.writeUTF(this.message);


			}


		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}
	
}