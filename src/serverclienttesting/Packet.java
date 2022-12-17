package serverclienttesting;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Packet implements Serializable{
    public String productID;
    public String bidderID;
    public String productName;
    
    public Packet(String productID, String bidderID, String productName){
        this.productID = productID;
        this.bidderID = bidderID;
        this.productName = productName;
    }
    
}
