package ModelsModule;

import java.sql.*;

public class DBconnect {

    private final String connectionUrl = "jdbc:sqlserver://localhost:1433;user=admin;password=admin;"
            + "databaseName=AuctionSystem;";

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(connectionUrl);
    }

}
