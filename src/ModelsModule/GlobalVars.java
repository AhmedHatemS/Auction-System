package ModelsModule;

import ModelsModule.DBconnect;
import java.sql.Connection;
import java.sql.*;

public class GlobalVars {
    public static DBconnect c1 = new DBconnect();
    public static Connection c;
    public static Statement ss;
    public static ResultSet rs;
    public static String query;
}
