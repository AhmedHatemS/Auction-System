package serverclienttesting;

/*/*
 * @(#)Server.java	1.0 10/12/09
 *
 * Psuedocode
 *   Start server socket on port 5000
 *   Listen for incoming connections
 *   Create new ClientHandler to manage each new connection
 *   Shutdown the server when done
 *   
 * UML Diagram
 * --------------------------------------------------
 *                     Server
 * --------------------------------------------------
 *  -server: ServerSocket
 *  -c: ClientHandler
 * --------------------------------------------------
 *  +main(String[]): void
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  -initial release
 **/
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket server = new ServerSocket(5000);
                
		while (true)
		{
			// wait for new client to connect
			Socket client = server.accept();
			System.out.println ("Accepted connection from: " + client.getInetAddress ());
			int coreCount = Runtime.getRuntime().availableProcessors(); //Count of available cores to use.                
                        ExecutorService threadPool = Executors.newFixedThreadPool(coreCount); //Create thread pool.                       
                        // create clienthandler to manage new incoming connection
			ClientHandler c = new ClientHandler(client);
                        threadPool.submit(c);
		}
	}
}