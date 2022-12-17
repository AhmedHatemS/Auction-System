/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModule;

import ControllerModule.RegisterController;
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
public class RegisterView extends JPanel {
    
    ImageIcon icon = new ImageIcon(getClass().getResource("/ViewModule/Online Auction System Register.jpg"));
    JLabel iconLabel = new JLabel();
    JLabel register = new JLabel("Register");
    JLabel userNameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JLabel emailLabel = new JLabel("Email");
    JLabel phoneLabel = new JLabel("Phone");
    JTextField userNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailField = new JTextField();
    JTextField phoneField = new JTextField();
    JButton submit = new JButton("Submit");
    JButton login = new JButton("Back To Login");
    String userName, userPassword, userEmail, userPhone;
    RegisterView.onButtonClick click = new RegisterView.onButtonClick();
    
    public RegisterView() {
        
        this.setBounds(0, 0, 750, 650);
        this.setLayout(null);
        this.setBackground(new Color(235, 241, 236));
        
        this.add(iconLabel);
        this.add(register);
        this.add(userNameLabel);
        this.add(passwordLabel);
        this.add(emailLabel);
        this.add(phoneLabel);
        this.add(userNameField);
        this.add(passwordField);
        this.add(emailField);
        this.add(phoneField);
        this.add(submit);
        this.add(login);
        
        iconLabel.setIcon(icon);
        iconLabel.setBounds(15, 35, 450, 550);
        
        register.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
        userNameLabel.setFont(new Font("candara", Font.PLAIN, 20));
        passwordLabel.setFont(new Font("candara", Font.PLAIN, 20));
        emailLabel.setFont(new Font("candara", Font.PLAIN, 20));
        phoneLabel.setFont(new Font("candara", Font.PLAIN, 20));
        userNameField.setFont(new Font("candara", Font.PLAIN, 20));
        passwordField.setFont(new Font("", Font.PLAIN, 20));
        emailField.setFont(new Font("candara", Font.PLAIN, 20));
        phoneField.setFont(new Font("candara", Font.PLAIN, 20));
        submit.setFont(new Font("candara", Font.PLAIN, 20));
        login.setFont(new Font("candara", Font.PLAIN, 15));
        
        
        register.setBounds(510, 70, 160, 50);
        userNameLabel.setBounds(490, 140, 150, 30);
        passwordLabel.setBounds(490, 210, 150, 30);
        emailLabel.setBounds(490, 280, 150, 30);
        phoneLabel.setBounds(490, 350, 150, 30);
        userNameField.setBounds(490, 165, 200, 30);
        passwordField.setBounds(490, 235, 200, 30);
        emailField.setBounds(490, 305, 200, 30);
        phoneField.setBounds(490, 375, 200, 30);
        submit.setBounds(490, 445, 200, 30);
        login.setBounds(545, 515, 90, 30);
        
        register.setForeground(new Color(13, 52, 60));
        userNameLabel.setForeground(new Color(13, 52, 60));
        userNameField.setForeground(new Color(13, 52, 60));
        passwordLabel.setForeground(new Color(13, 52, 60));
        passwordField.setForeground(new Color(13, 52, 60));
        emailLabel.setForeground(new Color(13, 52, 60));
        emailField.setForeground(new Color(13, 52, 60));
        phoneLabel.setForeground(new Color(13, 52, 60));
        phoneField.setForeground(new Color(13, 52, 60));
        submit.setBackground(new Color(13, 52, 60));
        submit.setForeground(new Color(255, 255, 255));
        submit.setBorder(null);
        login.setBackground(null);
        login.setForeground(new Color(13, 52, 60));
        login.setBorder(null);
        
        submit.addActionListener(click);
        login.addActionListener(click);
    }
    
    
    private class onButtonClick implements ActionListener {
       @Override
        public void actionPerformed(ActionEvent e) {
//            /////////////////////////////////////////////////////////////////////LOGIN PAGE BUTTONS
            if (e.getSource() == submit) {
               userName = userNameField.getText();
               userPassword = String.valueOf(passwordField.getPassword());
               userEmail = emailField.getText();
               userPhone = phoneField.getText();
               RegisterController register = new RegisterController();
                try {
                    if(!userName.equals("") && !userEmail.equals("") && !userPhone.equals("") && !userPassword.equals(""))
                        register.readingValues(userName, userEmail, userPhone, userPassword);
                    else
                        JOptionPane.showMessageDialog(null, "PLEASE FILL ALL CELLS!!");
                    
                    RegisterView.this.removeAll();
                    revalidate();
                    repaint();
                    LoginView loginPanel = new LoginView();
                    RegisterView.this.add(loginPanel);
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getSource() == login) {
                RegisterView.this.removeAll();
                revalidate();                           
                repaint();
                LoginView loginPanel = new LoginView();
                RegisterView.this.add(loginPanel);
            }
        }
    }
}
    
    
    
    

