package serverclienttesting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener, Runnable {

    /**
     * Stores the class serializable id.
     */
    private static final long serialVersionUID = 1397292566843594359L;

    /**
     * Stores the network socket used to interact with the rserver.
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
     * Stores a list of all received messages.
     */
    private LinkedList<String> messages;

    /**
     * Stores the main content panel containing the incoming and outgoing
     * message fields.
     */
    private JPanel contentPanel;

    /**
     * Stores the scroll pane for the received message display field.
     */
    private JScrollPane readFieldPane;

    /**
     * Stores the text area used for displaying received messages.
     */
    private JTextArea readField;

    /**
     * Stores the text field used for entering messages to be sent.
     */
    private JTextField writeField;

    private String productId;
    private String userId;
    private String productName;
    /**
     * Displays and runs the client application.
     */

    /**
     * Initializes the applet's components and then runs the applet.
     */
    public Client(String productId, String userId, String productName) {
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
        
        // initialize and setup content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // initialize and setup incoming message text area
        readField = new JTextArea();
        readField.setEditable(false);
        readField.setLineWrap(true);
        readField.setWrapStyleWord(true);
        readFieldPane = new JScrollPane(readField);
        readFieldPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        readFieldPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // initialize and setup outgoing message text field
        writeField = new JTextField();
        writeField.addActionListener(this);
        writeField.requestFocus();

        // add components to content panel
        contentPanel.add(readFieldPane, BorderLayout.CENTER);
        contentPanel.add(writeField, BorderLayout.SOUTH);

        // add components to frame
        getContentPane().add(contentPanel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 310));
        setVisible(true);
    }
    /**
     * Sends the entered message whenever enter is pressed inside of the input
     * box.
     */
    public void actionPerformed(ActionEvent evt) {
        try {
            if (!writeField.getText().isEmpty()) {
                outgoing.writeUTF(writeField.getText());
                writeField.setText("");
                outgoing.flush();
            }
        } catch (Exception e) {
            System.out.println("Problem sending message...");
        }
    }

    /**
     * Updates the message read field and refreshes the gui.
     */
    public void repaint() {
        readField.setText("");
        synchronized (messages) {
            ListIterator<String> itr = messages.listIterator();

            while (itr.hasNext()) {
                readField.setText(readField.getText() + itr.next() + "\n");
            }
        }
        readField.setCaretPosition(readField.getText().length() - 1);
        super.repaint();
    }

    /**
     * Runs the chat client.
     */
    public void run() {
        // connect to server
        try {
            messages = new LinkedList<String>();
            socket = new Socket("localhost", 5000);
            incoming = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            outgoing = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Packet packet = new Packet(productId, userId, productName);
            out.writeObject(packet);
            messages.add("Connected to server!");
            repaint();
        } catch (Exception e) {
            messages.add("Could not connect to server!");
            repaint();
            return;
        }

        // listen for incoming messages until socket is closed
        try {
            while (socket.isConnected() && !socket.isClosed() && !socket.isInputShutdown() && !socket.isOutputShutdown()) {
                messages.add(incoming.readUTF());
                repaint();
            }
        } catch (Exception e) {
        }

        // disconnected from server
        messages.add("Disconnected from server!");
        repaint();
    }

}
