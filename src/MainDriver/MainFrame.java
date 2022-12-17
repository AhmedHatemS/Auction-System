package MainDriver;

import ViewModule.LoginView;
import ModelsModule.DBExistence;
import ViewModule.BidView;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class MainFrame extends JFrame {

    LoginView loginPanel = new LoginView();

    public MainFrame() throws SQLException, ClassNotFoundException {
        //set icon
        try {
            Image image = new ImageIcon(this.getClass().getResource("/MainDriver/AuctionSystemIcon.png")).getImage();
            this.setIconImage(image);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "App icon not found!");
        }
        //Manage DBExistance
        DBExistence dbe = new DBExistence();
        //Frame settings
        this.setTitle("Auction system");
        this.setSize(750, 650);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocation(500, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(loginPanel);
}

}
