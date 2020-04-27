/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.SQLException;

/**
 *
 * @author tranb
 */
public class DBContext {

    InitialContext initial;
    Context context;
    String dbName, serverName, port, image, username, password;

    public DBContext() {
        try {
            initial = new InitialContext();
            context = (Context) initial.lookup("java:comp/env");
            serverName = (String) context.lookup("serverName");
            dbName = (String) context.lookup("dbName");
            port = (String) context.lookup("portNumber");
            username = (String) context.lookup("username");
            password = (String) context.lookup("password");
            image = (String) context.lookup("image");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://"
                + serverName + ":" + port + ";databaseName=" + dbName + ";username=" + username + ";password=" + password);
        return conn;
    }

    public String getImagePath() {
        return image;
    }

    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs == null || !rs.isClosed()) {
            rs.close();
        }
        if (ps == null || !ps.isClosed()) {
            ps.close();
        }
        if (con == null || !con.isClosed()) {
            con.close();
        }
    }
}
