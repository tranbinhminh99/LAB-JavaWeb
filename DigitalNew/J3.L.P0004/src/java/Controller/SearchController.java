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
public class SearchController extends HttpServlet {

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

            DAOArticle articleDb = new DAOArticle();
            int checkCurrentPage = 1;
            //get string keyword to search article
            String keyword = request.getParameter("keyword");
            //define number Aricle in one page
            int numberArticlesInPages = 3;

            //get number of pages by keyword
            int numberOfPages = articleDb.numberPages(numberArticlesInPages, keyword);

            String current = request.getParameter("page");

            if (current == null) {
                current = "1";
            }
            int currentpage = 0;
            try {
                currentpage = Integer.parseInt(current);
            } catch (Exception e) {
                checkCurrentPage = -1; //page is not integer
            }
            if (numberOfPages == 0) {
                checkCurrentPage = 2; //can't fount page
            } else {
                if (currentpage > numberOfPages && checkCurrentPage != -1) {
                    checkCurrentPage = 3; //pagenumber bigger than page founded
                }
            }

            //list article in page (search by keyword)
            ArrayList<Article> listArticle = articleDb.searchArticleByKeyWord(currentpage, numberArticlesInPages, keyword);
            //list top 5 newest article
            ArrayList<Article> top5Article = articleDb.getListArticles(5);
            //top 1 article
            Article top1 = articleDb.getTop1Article();
            request.setAttribute("news", top1);
            request.setAttribute("key", keyword);
            request.setAttribute("cur", currentpage);
            request.setAttribute("pages", numberOfPages);
            request.setAttribute("articles", top5Article);
            request.setAttribute("list", listArticle);
            //show message error
            if (checkCurrentPage == 2) { 
                request.setAttribute("error", "Can't not found any Article in pages");
            } else if (checkCurrentPage == 3) {
                request.setAttribute("error", "Page number bigger than pages are founded");
            } else if (checkCurrentPage == -1) {
                request.setAttribute("error","Page number must to an integer number");
            }
            request.getRequestDispatcher("search.jsp").forward(request, response);
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
