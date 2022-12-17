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
public class SellModel extends GlobalVars {
    public void Recording__User_Info(String id,String name, String price, String owner, String lastBid, String description) throws SQLException
    {

        
        c = c1.connect();
        
        //ss = c.createStatement();
        
        try{
            
           PreparedStatement insert_statement = c.prepareStatement("insert into productinfo (id , name , description , price ,available, owner_id , last_bid) values (?,?,?,?,1,?,?)");
           insert_statement.setString(1, id);
           insert_statement.setString(2, name);
           insert_statement.setString(3,description);               
           insert_statement.setString(4,price);
           insert_statement.setString(5,owner);
           insert_statement.setString(6,lastBid);
           insert_statement.executeUpdate();
       }
       catch (SQLException e )
      {
          System.out.println(e.getMessage());
      }
    }
}
