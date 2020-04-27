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
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "takeQuiz2", urlPatterns = {"/takeQuiz2"})
public class takeQuiz2 extends HttpServlet {

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
            out.println("<title>Servlet takeQuiz2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet takeQuiz2 at " + request.getContextPath() + "</h1>");
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
            HttpSession session = request.getSession();
            int number = Integer.parseInt(request.getParameter("number"));
            long timeSubmit = System.currentTimeMillis();
            long timeStart = (long) session.getAttribute("time");
            if (timeSubmit - (number*5*1000+800) <= timeStart) {
                int count = 0;
                int[] arrN = new int[number];
                arrN = (int[]) session.getAttribute("arrN");
                DAOOption dop = new DAOOption();
                for (int i = 0; i < arrN.length; i++) {
                    boolean flaq = true;
                    ArrayList<Option> listO = dop.getListOptions(arrN[i]);
                    //get question in bank with number of user
                    for (int j = 0; j < listO.size(); j++) {
                        String takeO = request.getParameter(listO.get(j).getO_id() + "");
                        //get option of question to compare with answer of user
                        boolean check;
                        if (takeO == null) {
                            //user no choose answer
                            check = false;
                        } else {
                            check = true;
                        }
                        if (!listO.get(j).isStatus() == check) {
                            //compare answers
                            flaq = false;
                        }
                    }
                    if (flaq) {
                        count++;
                    }
                }

                float score = (float) count / number * 100;
                String percent = String.format("%.0f", score);
                String checkPass;
                if (score >= 5) {
                    checkPass = "Passed";
                } else {
                    checkPass = "Not Passed";
                }
                //result of user
                String fomatScore = String.format("%.1f", (float) count / number * 10);
                String yourScore = fomatScore + " (" + percent + "%) - " + checkPass;
                //format result and send to web
                request.setAttribute("yourScore", yourScore);
                request.getRequestDispatcher("takeQuiz3.jsp").forward(request, response);
            } else {
                request.setAttribute("reject", "reject");
                request.getRequestDispatcher("takeQuiz3.jsp").forward(request, response);
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
