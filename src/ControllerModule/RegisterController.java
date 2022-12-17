/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllerModule;

import ModelsModule.GlobalVars;
import ModelsModule.RegisterModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tarek
 */
public class RegisterController extends GlobalVars {

//    private String UserName;
//    private String Email;
//    private String Phone;
//    private String password;
    
    public void readingValues (String name, String email, String phone, String password) throws SQLException
    {
        String id = GenerateUserId();
        JOptionPane.showMessageDialog(null, "Gongrats, you are registered successfully, Your ID is \" "+id+ " \"");
        RegisterModel model = new RegisterModel();
        model.Recording__User_Info(name, email, phone, password,id);
    }
    

//    public void setUserName(String UserName) {
//        this.UserName = UserName;
//    }
//
//    public void setEmail(String Email) {
//        this.Email = Email;
//    }
//
//    public void setPhone(String Phone) {
//        this.Phone = Phone;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
    
    
   
    
   //function that generate user id  
static String GenerateUserId()
 {
 int IdLength = 10;
 String AlphaNumericStr = "0123456789" ;
 
 StringBuilder s = new StringBuilder(IdLength);

 int i;

 for ( i=0; i<IdLength; i++) {
   int ch = (int)(AlphaNumericStr.length() * Math.random());
   s.append(AlphaNumericStr.charAt(ch));
  }
    return s.toString();
 }
    
}
