
package ModelsModule;

import java.time.LocalDateTime;

public class Bid {
    public String productID;
    public String bidderID;
    public int lastBid;
    public LocalDateTime timestamp;
    public boolean available;
    
    public Bid(String productID, String bidderID, int lastBid, boolean available){
        this.productID = productID;
        this.bidderID = bidderID;
        this.lastBid = lastBid;
        this.timestamp = LocalDateTime.now();
        this.available = available;
    }
}
