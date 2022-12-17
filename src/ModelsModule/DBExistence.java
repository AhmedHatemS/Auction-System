package ModelsModule;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBExistence extends GlobalVars {

    public DBExistence() throws SQLException, ClassNotFoundException {
        check();
    }

    private boolean DBExists() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=admin;password=admin;");
            ss = c.createStatement();
            rs = c.getMetaData().getCatalogs();
            while (rs.next()) {
                if ("AuctionSystem".equals(rs.getString("TABLE_CAT"))){
                    return true;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
        return false;
    }

    private void createDB() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=admin;password=admin;");
            ss = c.createStatement();
            if (c != null) {
                String[] queries = new String[10];//queries to create DB
                queries[0] = "create database AuctionSystem";
                queries[1] = "use AuctionSystem";
                queries[2] = "create table userinfo (\n" +
                            "id nvarchar(50) not null primary key,\n" +
                            "username nvarchar(50) not null,\n" +
                            "password nvarchar(50) not null,\n" +
                            "email nvarchar(50) not null,\n" +
                            "phone nvarchar(50) not null\n" +
                            ");";
                queries[3] = "create table productinfo (\n" +
                                "id nvarchar(50) not null primary key,\n" +
                                "name nvarchar(50) not null,\n" +
                                "description nvarchar(50) not null,\n" +
                                "price int not null,\n" +
                                "available bit not null,\n" +
                                "owner_id nvarchar(50) FOREIGN KEY REFERENCES userinfo(id),\n" +
                                "last_bid int not null,\n" +
                                "bidder_id nvarchar(50)\n" +
                                ");";
                for (int i = 0; i < queries.length; i++) {
                    query = queries[i];
                    ss.execute(query);
                }
                c = c1.connect();
                ss = c.createStatement();
                for (int i = 2; i < queries.length; i++) {
                    query = queries[i];
                    ss.execute(query);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }

    private void check() throws SQLException, ClassNotFoundException {
        if (!DBExists()) {
            JOptionPane.showMessageDialog(null, "Creating database now ...");
            createDB();
        }
    }
}
