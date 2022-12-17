/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllerModule;

import ModelsModule.SellModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tarek
 */
public class SellController {
    public void readingValues(String ownerid, String Prod_name,String Prod_price,String Prod_decription) throws SQLException{
        String ProdID = GenerateProductId();
        SellModel model = new SellModel();
        String Prod_owner = ownerid;
        String Prod_lastBid = Prod_price;
        model.Recording__User_Info(ProdID, Prod_name, Prod_price, Prod_owner, Prod_lastBid ,Prod_decription);
    }
    
    static String GenerateProductId()
 {
 int IdLength = 5;
 String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789" ;
 
 StringBuilder s = new StringBuilder(IdLength);

 int i;

 for ( i=0; i<IdLength; i++) {
   int ch = (int)(AlphaNumericStr.length() * Math.random());
   s.append(AlphaNumericStr.charAt(ch));
  }
    return s.toString();
 }
}
