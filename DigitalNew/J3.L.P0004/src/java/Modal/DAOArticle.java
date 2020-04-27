/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Entity.Article;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tranb
 */
public class DAOArticle extends DBContext{
    public ArrayList<Article> getListArticles(int numberArticles) throws Exception{
        ArrayList<Article> articles = new ArrayList<Article>();
        String sql = "SELECT top (?) id, title, image, [content], date, author, mieuta\n" +
                    "FROM Article1\n" +
                    "order by date desc";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, numberArticles);
            rs = statement.executeQuery();
            while(rs.next()){
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setImageUrl(rs.getString("image"));
                a.setContent(rs.getString("content"));
                a.setDate(rs.getTimestamp("date"));
                a.setAuthor(rs.getString("author"));
                a.setShortDes(rs.getString("mieuta"));
                articles.add(a);
            }
            return articles;
        } catch (Exception e) {
            throw e;
        }finally{
            closeConnection(conn, statement, rs);
        }
    }
    
    public Article getTop1Article() throws Exception{
        Article a = new Article();
        String sql = "SELECT top 1 id, title, image, [content], date, author, mieuta\n" +
                    "FROM Article1\n" +
                    "order by date desc";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            if(rs.next()){
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setImageUrl(rs.getString("image"));
                a.setContent(rs.getString("content"));
                a.setDate(rs.getTimestamp("date"));
                a.setAuthor(rs.getString("author"));
                a.setShortDes(rs.getString("mieuta"));
            }
            return a;
        } catch (Exception e) {
            throw e;
        }finally{
            closeConnection(conn, statement, rs);
        }
    }
    
    public Article viewArticleById(int id) throws Exception{
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from Article1 where id = ?";
        Article a = new Article();
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.next()){
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setImageUrl(rs.getString("image"));
                a.setContent(rs.getString("content"));
                a.setDate(rs.getTimestamp("date"));
                a.setAuthor(rs.getString("author"));
                a.setShortDes(rs.getString("mieuta"));
                return a;
            }
            else return null;
        } catch (Exception e) {
            throw  e;
        }finally{
            closeConnection(conn, statement, rs);
        }
    }
    
    
    
    public ArrayList<Article> searchArticleByKeyWord(int currentPage, int articlesInPage,String key) throws Exception{
        ArrayList<Article> articles = new ArrayList<Article>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from (\n" +
                    "        select ROW_NUMBER()\n" +
                    "        over(order by id) as Number, *\n" +
                    "        from Article1 where title like ?)\n" +
                    "        as Data where Number between (?) and (?)";
        try {
            conn = getConnection();
            int from = currentPage*articlesInPage - (articlesInPage-1);
            int to = from + articlesInPage - 1;
            statement = conn.prepareStatement(sql);
            statement.setString(1,  "%"+key+"%");
            statement.setInt(2, from);
            statement.setInt(3, to);
            
            rs = statement.executeQuery();
            while(rs.next()){
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setImageUrl(rs.getString("image"));
                a.setContent(rs.getString("content"));
                a.setDate(rs.getTimestamp("date"));
                a.setAuthor(rs.getString("author"));
                a.setShortDes(rs.getString("mieuta"));
                articles.add(a);
            }
            return articles;
        } catch (Exception e) {
            throw e;
        }
        finally{
            closeConnection(conn, statement, rs);
        }
    }
    
    public int numberPages(int articleInPage,String key) throws Exception{
        int count = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select count(id) as number from Article1 where title like (?)";
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%"+key+"%");
            rs = statement.executeQuery();
            if(rs.next()){
                count = rs.getInt("number");
            }
            return count % articleInPage == 0 ? count/articleInPage:(count/articleInPage+1);
        } catch (Exception e) {
            throw e;
        }finally{
            closeConnection(conn, statement, rs);
        }
    }
    
}
