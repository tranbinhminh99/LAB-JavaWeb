/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOOption;
import model.DAOQuestion;

/**
 *
 * @author tranb
 */
@WebServlet(name = "managerQuizController", urlPatterns = {"/manager"})
public class managerQuizController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet managerQuizController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet managerQuizController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            DAOQuestion dq = new DAOQuestion();
            int count = dq.countQuestion();
            int numberInPage = 4;
            String currentPage = request.getParameter("id");
            if (currentPage == null) {
                currentPage = "1";
            }
            int page = Integer.parseInt(currentPage);
            ArrayList<Question> listQ = dq.getListQuestionsPadding(page, numberInPage);
            //load list on page
            int pagecount = (count % numberInPage == 0) ? count / numberInPage : count / numberInPage + 1;
            //caculate munber of page
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            request.setAttribute("df", df);
            request.setAttribute("count", count);
            request.setAttribute("pagecount", pagecount);
            request.setAttribute("listQ", listQ);
            request.getRequestDispatcher("managerQuiz.jsp").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
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
        try {
            int q_id = Integer.parseInt(request.getParameter("delete"));
            //get id to delete question
            int numberInPage = 4;
            DAOOption dop = new DAOOption();
            dop.deleteOption(q_id);
            DAOQuestion dq = new DAOQuestion();
            dq.deleteQuestionById(q_id);
            int count = dq.countQuestion();
            String currentPage = request.getParameter("id");
            if (currentPage == null) {
                currentPage = "1";
            }
            int page = Integer.parseInt(currentPage);
            int pagecount = (count % numberInPage == 0) ? count / numberInPage : count / numberInPage + 1;
            ArrayList<Question> listQ = dq.getListQuestionsPadding(page, numberInPage);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            request.setAttribute("df", df);
            request.setAttribute("count", count);
            request.setAttribute("listQ", listQ);
            request.setAttribute("pagecount", pagecount);
            response.sendRedirect("./manager?id=" + page);

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
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
