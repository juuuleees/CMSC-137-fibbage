// File Name GreetingServer.java

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   private ArrayList<ChatMessage> chat_messages = new ArrayList<ChatMessage>();
   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
            // System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            
            /* Start accepting data from the ServerSocket */
            Socket server = serverSocket.accept();
            // System.out.println("Just connected to " + server.getRemoteSocketAddress());
            String sender_addr = server.getRemoteSocketAddress().toString();                                // save the sender's ip address
            
            /* Read data from the ClientSocket */
            // where it reads/recieves
            DataInputStream in = new DataInputStream(server.getInputStream());
            String sender_message = in.readUTF();                                                           // save the sender's message
            // System.out.println(in.readUTF());

            ChatMessage new_message = new ChatMessage(sender_addr, sender_message);                         // make a new ChatMessage object using the ip and message you saved
            chat_messages.add(new_message);                                                                 // add the ChatMessage object to the chat_message ArrayList
            
            // where it writes/sends
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
           
            /* Send data to the ClientSocket */
            // replies then closes server afterwards
            Scanner chat_string = new Scanner(System.in);                                                   // do the above for the server, but without saving the server's ip address
            System.out.print("Message: ");
   
            String message = chat_string.nextLine();

            ChatMessage server_message = new ChatMessage(message);
            chat_messages.add(server_message);

            for (ChatMessage c : chat_messages) {                                                           // print every message in the ArrayList
               out.writeUTF(c.message);                                                                     // yung problema dito is mapiprint lahat ng message including yung mga dating nasend
            }
            // server.close();

         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }

   // class ClientHandler extends Thread {
      
   // }
   
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}