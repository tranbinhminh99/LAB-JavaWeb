/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entity.Account;
import Entity.UserType;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tranb
 */
public class DAOAccount extends DBContext {

    DAOUserType du = new DAOUserType();

    //use for login form
    public Account getAccountByUser(String user) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "SELECT Account.UserName, Account.PassWord, Account.Email, UserType.Type, UserType.t_id\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  UserType ON Account.t_id = UserType.t_id where Account.UserName = ?";

            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            rs = statement.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("UserName"));
                a.setPassword(rs.getString("PassWord"));
                a.setEmail(rs.getString("Email"));
                UserType u = du.getUserTypeById(rs.getInt("t_id"));
                a.setA_Type(u);
                return a;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return null;
    }

    //check email exist
    public boolean checkEmail(String email) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "select Email from account\n"
                    + "where email = ?";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            rs = statement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return true;
    }

    //use for register form
    public void addAcount(Account a) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "INSERT INTO [dbo].[Account]\n"
                    + "           ([UserName]\n"
                    + "           ,[PassWord]\n"
                    + "           ,[Email]\n"
                    + "           ,[t_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, a.getUserName());
            statement.setString(2, a.getPassword());
            statement.setString(3, a.getEmail());
            statement.setInt(4, a.getA_Type().getT_id());
            statement.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
    }
}
