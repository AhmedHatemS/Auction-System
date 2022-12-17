/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModule;

import ControllerModule.RegisterController;
import ControllerModule.SellController;
import MainDriver.MainFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 *
 * @author x70rvs
 */
public class SellView extends JPanel {
    
    JLabel Sell = new JLabel("Sell Your Product");
    JLabel ProdNameLabel = new JLabel("Product Name ");
    JLabel ProdPriceLabel = new JLabel("Product Price ");
    JLabel ProdDescriptionLabel = new JLabel("Product Description ");
    JTextField ProdNameField = new JTextField();
    JTextField ProdPriceField = new JTextField();
    JTextField ProdDescriptionField = new JTextField();
    JButton Sell_Submit = new JButton("Sell");
    JButton Back = new JButton("Back");
    String ProdName, ProdPrice, ProdLastBid, ProdDescription;
    String userID;
    onButtonClick click = new onButtonClick();
  
    ImageIcon icon = new ImageIcon(getClass().getResource("/ViewModule/sell product.png"));
    JLabel header = new JLabel();

    public SellView(String uid) {
        
        
        this.setBounds(0, 0, 750, 650);
        this.setLayout(null);
        this.setBackground(new Color(235, 241, 236));
        
        this.userID = uid;
        this.add(Sell);
        this.add(ProdNameLabel);
        this.add(ProdPriceLabel);
        this.add(ProdNameField);
        this.add(ProdPriceField);
        this.add(ProdDescriptionLabel);
        this.add(ProdDescriptionField);
        this.add(Sell_Submit);
        this.add(Back);
        this.add(header);
         
        Sell.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 40));
        ProdNameLabel.setFont(new Font("candara", Font.PLAIN, 30));
        ProdPriceLabel.setFont(new Font("candara", Font.PLAIN, 30));
        ProdDescriptionLabel.setFont(new Font("candara", Font.PLAIN, 30));
        ProdNameField.setFont(new Font("candara", Font.PLAIN, 20));
        ProdPriceField.setFont(new Font("candara", Font.PLAIN, 20));
        ProdDescriptionField.setFont(new Font("candara", Font.PLAIN, 20));
        Sell_Submit.setFont(new Font("candara", Font.PLAIN, 30));
        Back.setFont(new Font("candara", Font.PLAIN, 30));

        
        
        //(x,y,width,hight)
        Sell.setBounds(150, 100, 500, 50);
        ProdNameLabel.setBounds(120, 180, 300, 50);
        ProdPriceLabel.setBounds(120, 280, 300, 50);
        ProdDescriptionLabel.setBounds(120, 380, 300, 50);
        ProdNameField.setBounds(420, 190, 300, 40);
        ProdPriceField.setBounds(420, 280, 300, 40);
        ProdDescriptionField.setBounds(420, 380, 300, 40);
        Sell_Submit.setBounds(470, 500, 150, 40);
        Back.setBounds(130, 500, 150, 40);
        
        header.setBounds(0, 0,780 , 100);
        header.setIcon(icon);
        
        
        ProdNameLabel.setForeground(new Color(13, 52, 60));
        ProdPriceLabel.setForeground(new Color(13, 52, 60));
        ProdDescriptionLabel.setForeground(new Color(13, 52, 60));
        ProdNameField.setForeground(new Color(13, 52, 60));
        ProdPriceField.setForeground(new Color(13, 52, 60));
        ProdDescriptionField.setForeground(new Color(13, 52, 60));
        
        Sell_Submit.setBackground(new Color(13, 52, 60));
        Back.setBackground(new Color(13, 52, 60));

        Sell_Submit.setForeground(new Color(255, 255, 255));
        Back.setForeground(new Color(255, 255, 255));

        Sell_Submit.setBorder(null);
        Back.setBorder(null);

        Sell_Submit.addActionListener(click);
        Back.addActionListener(click);

        
    }
    
    private class onButtonClick implements ActionListener {
       @Override
        public void actionPerformed(ActionEvent e) {
//            /////////////////////////////////////////////////////////////////////LOGIN PAGE BUTTONS
            if (e.getSource() == Sell_Submit) {
               ProdName = ProdNameField.getText();
               ProdPrice = ProdPriceField.getText();
               //ProdLastBid = ProdLastBidField.getText();
               ProdDescription = ProdDescriptionField.getText();
               SellController sell = new SellController();
                try {
                    if(!ProdName.equals("") && !ProdPrice.equals("") && !ProdDescription.equals(""))
                        sell.readingValues(userID, ProdName, ProdPrice, ProdDescription);
                    else
                        JOptionPane.showMessageDialog(null, "Please fill all cells.");
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                }
                SellView.this.removeAll();
                revalidate();                           
                repaint();
                ProductsView ProductPanel = new ProductsView(userID);
                SellView.this.add(ProductPanel);
               }
            if (e.getSource() == Back) {
                SellView.this.removeAll();
                revalidate();                           
                repaint();
                ProductsView ProductPanel = new ProductsView(userID);
                SellView.this.add(ProductPanel);
            }
        }
    }
    
}
