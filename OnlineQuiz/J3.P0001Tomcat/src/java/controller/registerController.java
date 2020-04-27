/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Account;
import Entity.UserType;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOAccount;
import model.DAOUserType;

/**
 *
 * @author tranb
 */
public class registerController extends HttpServlet {

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
            out.println("<title>Servlet registerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Registration.jsp").forward(request, response);
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
            DAOAccount da = new DAOAccount();

            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String email = request.getParameter("email");
            String id = request.getParameter("type");
            //get infomation of account
            String notification = null;

            if (user == null) {
                user = "";
            }
            if (email == null) {
                email = "";
            }
            if (id == null) {
                id = "";
            }

            Account check = da.getAccountByUser(user);
            // check username exist
            if (check != null) {
                notification = "Username is aready exist ! Please choose one new.";
                request.setAttribute("notification", notification);
                request.setAttribute("user", user);
                request.setAttribute("email", email);
                request.setAttribute("type", id);
                request.getRequestDispatcher("Registration.jsp").forward(request, response);
            } else if (!da.checkEmail(email)) {
                //check mail exist
                notification = "Email is aready exist ! Please check again.";
                request.setAttribute("notification", notification);
                request.setAttribute("user", user);
                request.setAttribute("email", email);
                request.setAttribute("type", id);
                request.getRequestDispatcher("Registration.jsp").forward(request, response);
            } else {
                DAOUserType du = new DAOUserType();
                UserType u = du.getUserTypeById(Integer.parseInt(id));
                Account a = new Account(user, pass, u, email);
                da.addAcount(a);
                //add account
                request.getRequestDispatcher("login").forward(request, response);
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
