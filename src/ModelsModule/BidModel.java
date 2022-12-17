
package ModelsModule;

import static ModelsModule.GlobalVars.c;
import static ModelsModule.GlobalVars.c1;
import static ModelsModule.GlobalVars.query;
import static ModelsModule.GlobalVars.rs;
import static ModelsModule.GlobalVars.ss;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BidModel {
 
 public static Object[] ShowBid (String productid)throws ClassNotFoundException, SQLException
    {       

        Object[] data = new Object[5];

        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "Select userinfo.username, productinfo.name  ,productinfo.price ,productinfo.last_bid ,productinfo.description from productinfo,userinfo where productinfo.id LIKE '" + productid + "'" ;
            rs = ss.executeQuery(query);

        while(rs.next()){

            data[0] = rs.getString(1);
            data[1] =rs.getString(2);
            data[2]=rs.getString(3);
            data[3]=rs.getString(4);
         
            data[4]=rs.getString(5);
}
          c.close();
        }
        catch (SQLException e) {System.out.print(e);
            }
        return data;
    }

    public static void UpdateBid (int new_bid,String productid, String bidderId)throws ClassNotFoundException, SQLException
    {
        c = c1.connect();
        ss = c.createStatement();

        try {
            query = "UPDATE productinfo SET available = 0, last_bid = '"+ new_bid +"', bidder_id = '"+ bidderId +"' WHERE id = '"+ productid +"';";
            ResultSet rs = ss.executeQuery(query);
                    
          c.close();
        }
        catch (SQLException e) {System.out.print(e);}
    }
}
