package ControllerModule;

import ModelsModule.GlobalVars;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController extends GlobalVars{
    private String userName;
    private String password;
    private String ID;

    public boolean login() {
        return false;
    }

    public boolean login(String userName, String password) throws SQLException, ClassNotFoundException {
        this.userName = userName;
        this.password = password;
        //connection
        c = c1.connect();
        //code
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (userinfo.id) AS rowsCount FROM userinfo where userinfo.username LIKE '" + this.userName + "' and userinfo.password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("rowsCount") == 1) {//succeeded
                UserID();
                JOptionPane.showMessageDialog(null, "Your login succeeded");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Your login failed.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                c.close();
                ss.close();
                rs.close();
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null, s);
            }
        }
        return false;
    }

    private void UserID() throws ClassNotFoundException, SQLException {

        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT userinfo.id AS id FROM userinfo WHERE userinfo.username LIKE '" + this.userName + "' and userinfo.password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.ID = rs.getString("id");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String returnUserID() {
        return this.ID;
    }
}
