/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelsModule;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tarek
 */
public class RegisterModel extends GlobalVars {
     public void Recording__User_Info(String name, String email, String phone, String password, String id) throws SQLException
    {
//        System.out.println(name);
//        System.out.println(email);
//        System.out.println(phone);
//        System.out.println(password);
//        System.out.println(id);
        
        c = c1.connect();
        
        //ss = c.createStatement();
        
        try{
            
           PreparedStatement insert_statement = c.prepareStatement("insert into userinfo (id , username , password , email , phone) values (?,?,?,?,?)");
           insert_statement.setString(1, id);
           insert_statement.setString(2, name);
           insert_statement.setString(3,password);               
           insert_statement.setString(4,email);
           insert_statement.setString(5,phone);
           insert_statement.executeUpdate();
       }
       catch (SQLException e )
      {
          System.out.println(e.getMessage());
      }
    }
}
