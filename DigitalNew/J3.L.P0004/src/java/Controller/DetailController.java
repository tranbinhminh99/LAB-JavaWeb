/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Article;
import Modal.DAOArticle;
import context.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tranb
 */
public class DetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int checkArticleID = 1;

            String id = request.getParameter("id");
            int articleID = 0;

            try {
                articleID = Integer.parseInt(id);
            } catch (Exception e) {
                checkArticleID = 2;        // id is not an integer
            }

            DAOArticle articleDB = new DAOArticle();

            Article articleDetailByID = new Article();
            if (checkArticleID != 2) {
                articleDetailByID = articleDB.viewArticleById(articleID); // view article when id = id from user
                if (articleDetailByID == null) {
                    checkArticleID = 3;   //not found article when id = id from user
                }
            }

            //top 5 newest article
            ArrayList<Article> top5 = articleDB.getListArticles(5);

            //top 1 article
            Article top1 = articleDB.getTop1Article();

            request.setAttribute("news", top1); //  article moi nhat
            request.setAttribute("articles", top5);

            if (checkArticleID == 2) { // send message error id not integer
                request.setAttribute("error", "Id must be an integer plase check again !");
            } else if (checkArticleID == 3) {// send message error aricle not found
                request.setAttribute("error", "No articles found");
            } else { // take article to server
                request.setAttribute("detail", articleDetailByID);
            }
            request.getRequestDispatcher("detail.jsp").forward(request, response);

        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
