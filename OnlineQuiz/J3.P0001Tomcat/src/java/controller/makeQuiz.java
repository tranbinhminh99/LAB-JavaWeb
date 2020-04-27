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
import model.DAOOption;
import model.DAOQuestion;

/**
 *
 * @author tranb
 */
public class makeQuiz extends HttpServlet {

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
            out.println("<title>Servlet makeQuiz</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet makeQuiz at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);
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
            // get time current from laptop
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);

            //get infomation question and answers from jsp
            String question = request.getParameter("question");
            ArrayList<String> option = new ArrayList<>();
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");
            option.add(option1);
            option.add(option2);
            option.add(option3);
            option.add(option4);
            ArrayList<String> cbx = new ArrayList<>();
            String cbxo1 = request.getParameter("cbxo1");
            String cbxo2 = request.getParameter("cbxo2");
            String cbxo3 = request.getParameter("cbxo3");
            String cbxo4 = request.getParameter("cbxo4");

            String message = "";
            boolean flaq = true;
            if (cbxo1 == null && cbxo2 == null && cbxo3 == null && cbxo4 == null) {
                message = "Your question don't have any answers ! ";
                flaq = false;
                request.setAttribute("message", message);
                request.setAttribute("question", question);
                request.setAttribute("listOption", option);
                request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);
            } else {
                if (cbxo1 == null) {
                    cbxo1 = "";
                }
                if (cbxo2 == null) {
                    cbxo2 = "";
                }
                if (cbxo3 == null) {
                    cbxo3 = "";
                }
                if (cbxo4 == null) {
                    cbxo4 = "";
                }
                cbx.add(cbxo1);
                cbx.add(cbxo2);
                cbx.add(cbxo3);
                cbx.add(cbxo4);
            }
            if (cbxo1.equals("true") && cbxo2.equals("true") && cbxo3.equals("true") && cbxo4.equals("true")) {
                flaq = false;
                message = "Your question have all answers is right ! ";
                request.setAttribute("message", message);
                request.setAttribute("question", question);
                request.setAttribute("listOption", option);
                boolean flaqcbx = false;
                request.setAttribute("flaqcbx", flaqcbx);
                request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);
            }
            if (flaq) {
                // add question to database 
                DAOQuestion dq = new DAOQuestion();
                Question q = new Question();
                q.setQ_content(question);
                q.setDate_Create(date);
                dq.addQuestion(q);
                int qid = dq.getIdQuestion();
                DAOOption dop = new DAOOption();
                //add option to database
                for (int i = 0; i < 4; i++) {
                    Option o = new Option();
                    //set content for option
                    o.setO_Content(option.get(i));
                    //set question number
                    o.setQ_id(dq.getQuestionById(qid));
                    if (cbx.get(i).equals("true")) {
                        //set value of satatus
                        o.setStatus(true);
                    } else {
                        o.setStatus(false);
                    }
                    dop.addOption(o);
                }
                //redirect to managerpage
                response.sendRedirect("manager");
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
