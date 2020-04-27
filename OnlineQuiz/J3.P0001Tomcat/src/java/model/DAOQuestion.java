/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DAOQuestion extends DBContext {

    public ArrayList<Question> getListQuestionsTop(int top) throws Exception {
        ArrayList listQ = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "SELECT top (?) q_id, [content], date_Create\n"
                    + "FROM     Question order by NEWID ( );";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, top);
            rs = statement.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQ_id(rs.getInt("q_id"));
                q.setQ_content(rs.getString("content"));
                q.setDate_Create(rs.getDate("date_Create"));
                listQ.add(q);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return listQ;
    }

    public ArrayList<Question> getListQuestionsPadding(int currentPage, int numberQuesInPage) throws Exception {
        ArrayList listQ = new ArrayList();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "select * from \n"
                    + "   (select ROW_NUMBER() over(order by q_id) as Number, * \n"
                    + "                          from Question  ) as data where Number between (?) and (?)";

            int from = currentPage * numberQuesInPage - (numberQuesInPage - 1);
            int to = from + numberQuesInPage - 1;
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, from);
            statement.setInt(2, to);
            rs = statement.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQ_id(rs.getInt("q_id"));
                q.setQ_content(rs.getString("content"));
                q.setDate_Create(rs.getDate("date_Create"));
                listQ.add(q);
            }
        } catch (Exception ex) {

            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return listQ;
    }

    public int countQuestion() throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(q_id) as numberQ\n"
                    + "FROM Question";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception ex) {           
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return count;

    }

    public Question getQuestionById(int q_id) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "SELECT q_id, [content], date_Create\n"
                    + "FROM     Question where q_id = ?";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, q_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQ_id(rs.getInt("q_id"));
                q.setQ_content(rs.getString("content"));
                q.setDate_Create(rs.getDate("date_Create"));
                return q;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return null;
    }

    public void deleteQuestionById(int q_id) throws Exception {
        DAOOption doo = new DAOOption();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            doo.deleteOption(q_id);
            String sql = "DELETE FROM [dbo].[Question]\n"
                    + "      WHERE q_id = ?";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, q_id);
            statement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
    }

    public void addQuestion(Question q) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            String sql = "INSERT INTO [dbo].[Question]\n"
                    + "           ([content]\n"
                    + "           ,[date_Create])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, q.getQ_content());
            statement.setDate(2, q.getDate_Create());
            statement.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
    }

    public int getIdQuestion() throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        int id = 0;
        try {
            String sql = "SELECT IDENT_CURRENT('Question') AS Current_Identity";
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Current_Identity");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, statement, conn);
        }
        return id;
    }

}
