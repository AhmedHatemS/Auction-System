package serverclienttesting;

/*
 * @(#)ClientHandler.java	1.0 10/12/09
 *
 * Psuedocode
 *   Create input stream managers upon construction
 *   Listen for new messages from client
 *   Broadcast new messages from client to all connected clients
 *   Disconnect client upon closing of client socket
 *   
 * UML Diagram
 * --------------------------------------------------
 *                  ClientHandler
 * --------------------------------------------------
 *  -handles: LinkedList<String>
 *  -clients: LinkedList<ClientHandler>
 *  -socket: Socket
 *  -incoming: DataInputStream
 *  -outgoing: DataOutputStream
 *  -handle: String
 * --------------------------------------------------
 *  #broadcast(String): void
 *  -isValidHandle(String): boolean
 *  +ClientHandler(Socket)
 *  +run(): void
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  -initial release
 */
import ModelsModule.BidModel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.System.exit;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverclienttesting.GlobalValues;
import serverclienttesting.Packet;

public class ClientHandler extends Thread {

    /**
     * Stores the list of currently used handles as to keep them unique.
     */
    //will be our users id or username
    private static LinkedList<String> handles = new LinkedList<String>();

    /**
     * Stores the list of all currently active ClientHandler instances.
     */
    private static LinkedList<ClientHandler> clients = new LinkedList<ClientHandler>();

    /**
     * Stores the network socket used to interact with the client.
     */
    private Socket socket;

    /**
     * Stores the input stream reader used to receive network messages.
     */
    private DataInputStream incoming;

    /**
     * Stores the output stream writer used to send network messages.
     */
    private DataOutputStream outgoing;

    /**
     * Stores the handle currently being used by the client.
     */
    String handle;
    int lastBid = 0;

    /**
     * Broadcasts the specified message to all connected clients.
     *
     * @param message the message to broadcast
     */
    protected static synchronized void broadcast(String message) {
        synchronized (clients) {
            ListIterator<ClientHandler> itr = clients.listIterator();
            ClientHandler current;

            while (itr.hasNext()) {
                try {
                    current = itr.next();
                    current.outgoing.writeUTF(message);
                    current.outgoing.flush();
                } catch (Exception e) {
                    System.err.println("Rut roh...");
                }
            }
        }
    }

    /**
     * Checks if the specified handle is valid.
     */
    private static boolean isValidHandle(String handle) {
        for (int i = 0; i < handle.length(); i++) {
            if (!Character.isLetterOrDigit(handle.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Constructs a new client handler to service the specified client.
     */
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        incoming = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outgoing = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        handle = null;

    }

    /**
     * Listens for messages from the client and broadcasts them to all connected
     * clients.
     */
    public void run() {

        try {
            clients.add(this);

            String message;
            ObjectInputStream in = null;
            in = new ObjectInputStream(socket.getInputStream());
            Packet recvPacket = (Packet) in.readObject();
            handle = recvPacket.bidderID;
            System.out.println("Sent from the client: " + recvPacket.bidderID);
            GlobalValues.msTimeLastUsed = System.currentTimeMillis();
            while (true) {
                if (incoming.available() != 0) {
                    message = incoming.readUTF();
                    int value = Integer.parseInt(message);
                    int LastBid = GlobalValues.LastBid;
                    GlobalValues.msTimeLastUsed = System.currentTimeMillis();
                    if (value <= GlobalValues.LastBid) {
                        continue;
                    } else {
                        broadcast("[" + handle + "] says: " + message);
                        GlobalValues.LastBid = value;
                        GlobalValues.lastBidderID = handle;
                        message = " ";
                    }
                    broadcast("Last Bid Value: " + GlobalValues.LastBid);
                } else if (System.currentTimeMillis() - GlobalValues.msTimeLastUsed >= 1000 * 10) {
                    broadcast("Product " + recvPacket.productName + " is for "+GlobalValues.lastBidderID+" paid "+ GlobalValues.LastBid);                 
                    clients.clear();
                    socket.close();
                    saveToDatabase(recvPacket);
                    exit(0);
                }

            }

        } catch (IOException |ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            clients.remove(this);
            handles.remove(handle);
            if (handle != null) {
                broadcast("[" + handle + "] has left the room!");
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveToDatabase(Packet packet) throws SQLException, ClassNotFoundException {
        new BidModel().UpdateBid(GlobalValues.LastBid, packet.productID, GlobalValues.lastBidderID);
    }
}
