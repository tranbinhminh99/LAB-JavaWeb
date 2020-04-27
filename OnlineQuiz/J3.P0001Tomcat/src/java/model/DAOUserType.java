/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entity.UserType;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranb
 */
public class DAOUserType extends DBContext {

    public ArrayList<UserType> getListUserType() throws Exception {
        ArrayList listType = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {

            String sql = "SELECT [t_id]\n"
                    + "      ,[Type]\n"
                    + "  FROM [dbo].[UserType]";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                UserType ut = new UserType();
                ut.setT_id(rs.getInt("t_id"));
                ut.setType(rs.getString("Type"));
                listType.add(ut);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return listType;
    }

    public UserType getUserTypeById(int id) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            UserType ut = new UserType();
            String sql = "SELECT [t_id]\n"
                    + "      ,[Type]\n"
                    + "  FROM [dbo].[UserType]\n"
                    + "  where t_id = ?";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                ut.setT_id(rs.getInt("t_id"));
                ut.setType(rs.getString("Type"));
                return ut;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return null;
    }
}
