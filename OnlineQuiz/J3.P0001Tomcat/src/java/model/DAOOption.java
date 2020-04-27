/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entity.Option;
import Entity.Question;
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
public class DAOOption extends DBContext {

    public ArrayList<Option> getListOptionsByQ_Id(int[] arrN) throws Exception {
        DAOQuestion dq = new DAOQuestion();
        ArrayList listO = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "SELECT o_id, O_content, q_id, status\n"
                    + "FROM     [Option] where q_id in (";
            for (int i = 0; i < arrN.length - 1; i++) {
                sql = sql + "?,";
            }
            sql = sql + "?)";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < arrN.length; i++) {
                statement.setInt(i + 1, arrN[i]);
            }
            rs = statement.executeQuery();
            while (rs.next()) {
                Option o = new Option();
                Question q = dq.getQuestionById(rs.getInt("q_id"));
                o.setO_Content(rs.getString("O_content"));
                o.setO_id(rs.getInt("o_id"));
                o.setStatus(rs.getBoolean("status"));
                o.setQ_id(q);
                listO.add(o);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return listO;
    }

    public ArrayList<Option> getListOptions(int q_id) throws Exception {
        DAOQuestion dq = new DAOQuestion();
        ArrayList listO = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        {
            try {
                String sql = "SELECT [o_id]\n"
                        + "      ,[O_content]\n"
                        + "      ,[q_id]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[Option] where q_id = ?";
                conn = getConnection();
                statement = conn.prepareStatement(sql);
                statement.setInt(1, q_id);
                rs = statement.executeQuery();
                while (rs.next()) {
                    Option o = new Option();
                    Question q = dq.getQuestionById(rs.getInt("q_id"));
                    o.setO_Content(rs.getString("O_content"));
                    o.setO_id(rs.getInt("o_id"));
                    o.setStatus(rs.getBoolean("status"));
                    o.setQ_id(q);
                    listO.add(o);
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                closeConnection(rs, statement, conn);
            }
        }
        return listO;
    }

    public void deleteOption(int q_id) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "DELETE FROM [dbo].[Option]\n"
                    + "      WHERE q_id = ?";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, q_id);
            statement.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
    }

    public void addOption(Option o) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "INSERT INTO [dbo].[Option]\n"
                    + "           ([O_content]\n"
                    + "           ,[q_id]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, o.getO_Content());
            statement.setInt(2, o.getQ_id().getQ_id());
            statement.setBoolean(3, o.isStatus());
            statement.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
    }

}
