import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketException; 
import java.util.Scanner;

public class DatagramClient {

	private static DatagramSocket client;
	private static byte[] data_buffer;
	private static DatagramPacket sender_packet;

	public static void main(String[] args) {

		try {

			// make the socket
			client = new DatagramSocket();

			InetAddress ip = InetAddress.getLocalHost();
			data_buffer = null;

			Scanner user_input = new Scanner(System.in);

			while (true) {

				// get input and convert to bytes
				System.out.print("Message: ");
				String msg_data = user_input.nextLine();


				System.out.println(msg_data);
				if (msg_data.equals("bye")) {
					data_buffer = msg_data.getBytes();
	
					// make the datagram
					sender_packet = new DatagramPacket(data_buffer, data_buffer.length, ip, 51519);
	
					// (hopefully) send the datagram
					client.send(sender_packet);
				} else {
					break;
				}

			}
			

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

}