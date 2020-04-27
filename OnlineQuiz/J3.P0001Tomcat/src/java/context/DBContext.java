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

/**
 *
 * @author tranb
 */
public class DBContext   {

    InitialContext initial;
    Context context;
    String databaseName, serverName, portNumber, image, username, password;

    public DBContext(){
        try {
            initial = new InitialContext();
            context = (Context) initial.lookup("java:comp/env");
            databaseName = context.lookup("dbName").toString();
            serverName = context.lookup("serverName").toString();
            portNumber = context.lookup("portNumber").toString();
            username = context.lookup("username").toString();
            password = context.lookup("password").toString();
            image = context.lookup("image").toString();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://"
                + serverName + ":" + portNumber + ";databaseName=" + databaseName + ";username=" + username + ";password=" + password);
        return conn;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws Exception {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !ps.isClosed()) {
            con.close();
        }
    }

    public String getResource() throws Exception {
        return image;
    }
}
