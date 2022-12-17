package ViewModule;

import ControllerModule.BidController;
import ControllerModule.SellController;
import ModelsModule.productInfo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BidView extends JPanel{
    JLabel Sell = new JLabel("Add Bid");
    JLabel OwnerNameLabel = new JLabel("owner Name ");
    JLabel ProdNameLabel = new JLabel("Product Name ");
    JLabel ProdPriceLabel = new JLabel("Product Price ");
    JLabel ProdLastBidLabel = new JLabel("Last Bid ");
    JLabel ProdDescriptionLabel = new JLabel("Product Description ");

    JTextField ProdLastBidField = new JTextField();
    String ProdName, ProdPrice, ProdLastBid, ProdDescription ,owner_name , new_bid;

    JButton Submit = new JButton("new bid");

    BidView.onButtonClick click = new BidView.onButtonClick();



    public BidView(){
        this.setBounds(0, 0, 750, 650);
        this.setBackground(new Color(115, 194, 251));
        this.add(Sell);
        this.add(OwnerNameLabel);
        this.add(ProdNameLabel);
        this.add(ProdPriceLabel);
        this.add(ProdLastBidLabel);
        this.add(ProdDescriptionLabel);
        this.add(ProdLastBidField);
        this.add(Submit);
        Submit.addActionListener(click);

        Object [] product = new Object[5];
        try {
        product = BidController.Bid("2");
         }
        catch (SQLException e) {System.out.print(e);} catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setLayout(null);

        JLabel owner = new JLabel((String) product[0]);
        JLabel prodName = new JLabel((String) product[1]);
        JLabel prodPrice = new JLabel((String) product[2]);
        JLabel prodLastBid = new JLabel((String) product[3]);
        JLabel prodDescription = new JLabel((String) product[4]);
        this.add(owner);
        this.add(prodName);
        this.add(prodPrice);
        this.add(prodLastBid);
        this.add(prodDescription);

        owner.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        prodName.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        prodPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        prodLastBid.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        prodDescription.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        ProdLastBidField.setFont(new Font("", Font.PLAIN, 20));

    
        owner.setBounds(290, 50, 170, 50);
        prodName.setBounds(290, 150, 170, 50);
        prodPrice.setBounds(290, 250, 170, 50);
        prodLastBid.setBounds(290, 350, 170, 50);
        prodDescription.setBounds(290, 450, 170, 50);

        ProdLastBidField.setBounds(100, 500, 300, 50);
        OwnerNameLabel.setBounds(100, 50, 300, 50);
        ProdNameLabel.setBounds(100, 150, 300, 50);
        ProdPriceLabel.setBounds(100, 250, 300, 50);
        ProdLastBidLabel.setBounds(100, 350, 300, 50);
        ProdDescriptionLabel.setBounds(100, 450, 300, 50);
        
        Submit.setBounds(300, 450, 300, 50);





    }

     private class onButtonClick implements ActionListener {
       @Override
         public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Submit) {
            
               new_bid = ProdLastBidField.getText();
               BidController bid = new BidController();
             try{
                 bid.updateBid(Integer.parseInt(new_bid),"2", "");
             } catch (SQLException ex) {
                    Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BidView.class.getName()).log(Level.SEVERE, null, ex);
                }
                BidView.this.removeAll();
                revalidate();                           
                repaint();
             
               
        }
        }
    
}
}

