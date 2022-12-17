/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelsModule;

/**
 *
 * @author sohai
 */
public class productInfo {
    public String productId;
    public String owner_name;
    public String name;
    public String last_bid;

public productInfo(String productId, String username , String name, String last_bid) {
    this.productId = productId;
        this.owner_name = username;
        this.name = name;
        this.last_bid = last_bid;
    }

 

}
