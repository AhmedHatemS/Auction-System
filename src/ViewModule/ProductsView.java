/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModule;

import ControllerModule.productController;
import ModelsModule.productInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import serverclienttesting.Client;

/**
 *
 * @author sohai
 */
public class ProductsView extends JPanel {

    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);
    ArrayList<productInfo> products = new ArrayList();

    JLabel Products = new JLabel("Products");
    JButton logButton = new JButton("Log Out");
    JButton sellButton = new JButton("Sell item");
    JScrollPane sp = new JScrollPane(table);
    private ActionListener v;
    String userID;

    ImageIcon icon = new ImageIcon(getClass().getResource("/ViewModule/products.png"));
    JLabel header = new JLabel();

    public ProductsView(String uid) {

        this.userID = uid;
        this.setBounds(0, 0, 750, 650);
        this.setLayout(null);
        this.setBackground(new Color(235, 241, 236));

        this.add(Products);
        this.add(logButton);
        this.add(sellButton);
        this.add(header);
        this.add(sp, BorderLayout.CENTER);

        onButtonClick logoutclick = new onButtonClick();
        logButton.addActionListener(logoutclick);

        onButtonClick sellclick = new onButtonClick();
        sellButton.addActionListener(sellclick);

        Products.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 40));
        logButton.setFont(new Font("candara", Font.PLAIN, 30));
        sellButton.setFont(new Font("candara", Font.PLAIN, 30));
        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        sp.setFont(new Font("Tahoma", Font.PLAIN, 15));

        Products.setBounds(250, 100, 250, 50);
        logButton.setBounds(120, 560, 150, 40);
        sellButton.setBounds(470, 560, 150, 40);
        table.setBounds(120, 150, 500, 400);
        header.setBounds(0, 0, 780, 100);
        sp.setBounds(table.getBounds());

        logButton.setBackground(new Color(13, 52, 60));
        logButton.setForeground(new Color(255, 255, 255));

        sellButton.setBackground(new Color(13, 52, 60));
        sellButton.setForeground(new Color(255, 255, 255));

        table.setBackground(new java.awt.Color(238, 238, 238));
        table.setForeground(new java.awt.Color(13, 52, 60));

        sp.setBackground(new java.awt.Color(235, 241, 236));
        sp.setForeground(new java.awt.Color(235, 241, 236));

        //  table.setAutoCreateRowSorter(true);
        header.setIcon(icon);

        Dimension dim = new Dimension(100, 0);
        table.setIntercellSpacing(new Dimension(dim));

        sp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sp.setToolTipText("");
        // Data to be displayed in the JTable

        try {
            products = productController.print_products();
        } catch (SQLException e) {
            System.out.print(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsView.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableModel.addColumn("OWNER_NAME");
        tableModel.addColumn("PRODUCT_NAME");
        tableModel.addColumn("LAST_BID");
        for (int i = 0; i < products.size(); i++) {
            tableModel.insertRow(i, new Object[]{products.get(i).owner_name, products.get(i).name, products.get(i).last_bid});

        }
        final ListSelectionModel model = table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!model.isSelectionEmpty()) {
                    int selectedRow = model.getMinSelectionIndex();
                    JOptionPane.showMessageDialog(null, "You are to start bid for "+products.get(selectedRow).name +"!");
                    new Thread(new Client(
                            products.get(
                                    selectedRow).productId, uid,
                                    products.get(selectedRow).name
                            )
                    ).start();
                }
            }

        });

    }

    private class onButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            logButton.addActionListener(v);
            if (e.getSource() == logButton) {

                ProductsView.this.removeAll();
                revalidate();
                repaint();
                ProductsView.this.setLayout(null);
                LoginView loginPanel = new LoginView();
                ProductsView.this.add(loginPanel);
            } else if (e.getSource() == sellButton) {

                ProductsView.this.removeAll();
                revalidate();
                repaint();
                ProductsView.this.setLayout(null);
                SellView sellPanel = new SellView(userID);
                ProductsView.this.add(sellPanel);
            }
        }
    }
}
