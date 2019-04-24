import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GreetingClient
{
   public static void main(String [] args)
   {
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);

      while (true) {

         try
         {
            /* Open a ClientSocket and connect to ServerSocket */
            // System.out.println("Connecting to " + serverName + " on port " + port);
            // make an instance of Socket
            Socket client = new Socket(serverName, port);
            System.out.println(client.isConnected());
       
      /* Send data to the ServerSocket */
            // System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
   
            Scanner chat_string = new Scanner(System.in);
            System.out.print("Message: ");
   
            String message = chat_string.nextLine();
   
            out.writeUTF("Client: " + message);
            
      /* Receive data from the ServerSocket */
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println(in.readUTF());
            // client.close();
   
         }catch(IOException e)
         {
            e.printStackTrace();
         }

      }

   }
}
