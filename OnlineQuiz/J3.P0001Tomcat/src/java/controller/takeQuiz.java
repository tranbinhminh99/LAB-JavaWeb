/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Option;
import Entity.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOOption;
import model.DAOQuestion;

/**
 *
 * @author tranb
 */
public class takeQuiz extends HttpServlet {

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
            out.println("<title>Servlet takeQuiz</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet takeQuiz at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
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

            int number = Integer.parseInt(request.getParameter("numberQ"));
            long millis = System.currentTimeMillis();
            HttpSession sessionArrN = request.getSession();
            HttpSession sessionTime = request.getSession();

            //get number user input
            DAOQuestion dq = new DAOQuestion();
            ArrayList<Question> listQ = dq.getListQuestionsTop(number);
            //check number question in bank and compare with number question user input 
            int countQ = dq.countQuestion();
            if (number > countQ) {
                String notification = "Not enough question for you";
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
            } else {
                int[] arrN = new int[number];
                for (int i = 0; i < number; i++) {
                    //take id of question in bank
                    arrN[i] = listQ.get(i).getQ_id();
                }
                sessionArrN.setAttribute("arrN", arrN);
                DAOOption dop = new DAOOption();
                ArrayList<Option> listO = dop.getListOptionsByQ_Id(arrN);
                //get list question
                request.setAttribute("listQ", listQ);
                request.setAttribute("listO", listO);
                request.setAttribute("number", number);
                sessionTime.setAttribute("time", millis);
                request.getRequestDispatcher("takeQuiz2.jsp").forward(request, response);
            }
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
