/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllerModule;

import ModelsModule.productInfo;
import java.sql.SQLException;
import ModelsModule.productsModel;
import java.util.ArrayList;

/**
 *
 * @author sohai
 */
public class productController {
       public static ArrayList print_products () throws ClassNotFoundException, SQLException
    {

           ArrayList product = productsModel.index();
        
        return product;
    }


}
