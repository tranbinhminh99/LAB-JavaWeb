package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.GalleryDAO;
import model.InfoDAO;

/**
 *
 * @author tranb
 */
public class HomeController extends HttpServlet {

    final int numberItemInPage = 3;
    GalleryDAO gdao = null;
    InfoDAO idao = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int check = 1;
            int page = 1;
            if (request.getParameter("page") == null) {
                page = 1;
            } else {
                String id = request.getParameter("page");
                try {
                    page = Integer.parseInt(id);
                } catch (Exception e) {
                    check = 2;
                }
            }
            gdao = new GalleryDAO();
            int numberpage = gdao.getNumberPage(numberItemInPage);
            request.setAttribute("topThree", gdao.getTopThree());
            if (numberpage < page) {
                check = 3;
            }
            if (page < 1) {
                check = 4;
            }
            if (check == 2) {
                request.setAttribute("error", "Page must be integer number !");
            } else if (check == 3) {
                request.setAttribute("error", "Page number bigger than numbers of page !");
            } else if (check == 4) {
                request.setAttribute("error", "Number page must be bigger than 0 !");
            } else {
                idao = new InfoDAO();
                request.setAttribute("listGallery", gdao.getListGallerySearch(numberItemInPage, page));
                request.setAttribute("numberPage", gdao.getNumberPage(numberItemInPage));
                request.setAttribute("info", idao.getInfo());
                request.setAttribute("page", page);
            }
            request.setAttribute("linkActive", "home");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
