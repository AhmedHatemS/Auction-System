/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelsModule;

import ModelsModule.GlobalVars;
import ControllerModule.productController;
import static ModelsModule.GlobalVars.c;
import static ModelsModule.GlobalVars.query;
import static ModelsModule.GlobalVars.ss;
import static ModelsModule.GlobalVars.rs;
import static ModelsModule.GlobalVars.c1;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sohai
 */
public class productsModel {

    public static ArrayList index() throws ClassNotFoundException, SQLException {
        ArrayList<productInfo> products = new ArrayList();
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "Select productinfo.id, userinfo.username, productinfo.name  ,productinfo.price ,productinfo.last_bid from productinfo,userinfo where productinfo.owner_id = userinfo.id AND productinfo.available = 'True';";
            rs = ss.executeQuery(query);

            while (rs.next()) {
                if ("0".equals(rs.getString(5))) {

                    products.add(new productInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                } else {
                    products.add(new productInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5)));

                }
            }

            c.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return products;
    }
}
