/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllerModule;

import ModelsModule.BidModel;
import java.sql.SQLException;

/**
 *
 * @author sohai
 */
        public class BidController {
               public static Object [] Bid (String productid) throws ClassNotFoundException, SQLException
            {

                   Object [] Bidshow = BidModel.ShowBid(productid);            

                return Bidshow;
            }

        public static void updateBid(int new_bid,String productid, String bidderId) throws ClassNotFoundException, SQLException{
                   BidModel.UpdateBid(new_bid,productid, bidderId);

        }
}
