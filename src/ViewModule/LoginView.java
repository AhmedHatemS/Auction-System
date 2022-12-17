package ViewModule;

import ControllerModule.LoginController;
import MainDriver.MainFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LoginView extends JPanel {
    
    ImageIcon icon = new ImageIcon(getClass().getResource("/ViewModule/Online Auction System.png"));
    JLabel iconLabel = new JLabel();
    
    JLabel welcome = new JLabel("Welcome");
    JLabel userNameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login!");
    JButton register = new JButton("Register Now");
    String username, password;

    onButtonClick click = new onButtonClick();
    LoginController lg = new LoginController();

    public LoginView() {
        
        this.setBounds(0, 0, 750, 650);
        this.setLayout(null);
        this.setBackground(new Color(235, 241, 236));
        this.add(iconLabel);
        this.add(welcome);
        this.add(userNameLabel);
        this.add(passwordLabel);
        this.add(userNameField);
        this.add(passwordField);
        this.add(loginButton);
        this.add(register);

        iconLabel.setIcon(icon);
        iconLabel.setBounds(25, 50, 450, 500);
        
        welcome.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
        userNameLabel.setFont(new Font("candara", Font.PLAIN, 20));
        passwordLabel.setFont(new Font("candara", Font.PLAIN, 20));
        userNameField.setFont(new Font("candara", Font.PLAIN, 20));
        passwordField.setFont(new Font("", Font.PLAIN, 20));
        loginButton.setFont(new Font("candara", Font.PLAIN, 20));
        register.setFont(new Font("candara", Font.PLAIN, 15));

        welcome.setBounds(505, 130, 170, 50);
        userNameLabel.setBounds(490, 205, 150, 30);
        passwordLabel.setBounds(490, 275, 150, 30);
        userNameField.setBounds(490, 230, 200, 30);
        passwordField.setBounds(490, 300, 200, 30);
        loginButton.setBounds(490, 370, 200, 30);
        register.setBounds(540, 440, 100, 30);

        welcome.setForeground(new Color(13, 52, 60));
        userNameLabel.setForeground(new Color(13, 52, 60));
        userNameField.setForeground(new Color(13, 52, 60));
        passwordLabel.setForeground(new Color(13, 52, 60));
        passwordField.setForeground(new Color(13, 52, 60));
        loginButton.setBackground(new Color(13, 52, 60));
        loginButton.setForeground(new Color(255, 255, 255));
        register.setBackground(null);
        register.setForeground(new Color(13, 52, 60));
        loginButton.setBorder(null);
        register.setBorder(null);

        loginButton.addActionListener(click);
        register.addActionListener(click);
    }

    private class onButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /////////////////////////////////////////////////////////////////////LOGIN PAGE BUTTONS
            if (e.getSource() == loginButton) {
                username = userNameField.getText();
                password = String.valueOf(passwordField.getPassword());
                try {
                    userNameField.setText("");
                    passwordField.setText("");
                    if (lg.login(username, password)) {
                        LoginView.this.removeAll();
                        revalidate();
                        repaint();
                        ProductsView ProductsView = new ProductsView(lg.returnUserID());
                        LoginView.this.add(ProductsView);
                    }      
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getSource() == register) {
                LoginView.this.removeAll();
                revalidate();                           
                repaint();
                RegisterView registerPanel = new RegisterView();
                LoginView.this.add(registerPanel);
            }
        }
    }
}
