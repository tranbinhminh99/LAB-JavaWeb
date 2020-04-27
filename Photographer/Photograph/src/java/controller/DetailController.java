package controller;

import entity.Gallery;
import entity.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.GalleryDAO;

/**
 *
 * @author tranb
 */
public class DetailController extends HttpServlet {

    GalleryDAO gdao = null;
    final int numberImageInAPage = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            gdao = new GalleryDAO();
            request.setAttribute("topThree", gdao.getTopThree());
            //validateID
            int checkId = 1;
            int id = 1;
            if (request.getParameter("id") == null) {
                id = 1;
            } else {
                String raw_id = request.getParameter("id");
                try {
                    id = Integer.parseInt(raw_id);
                } catch (Exception e) {
                    checkId = 2;
                }
            }
            if (id < 1) {
                checkId = 3;
            }
            if (!gdao.checkGalleryByID(id) && checkId != 3) {
                checkId = 4;
            }
            if (checkId == 2) {
                request.setAttribute("error", "ID must be a number !");
            } else if (checkId == 3) {
                request.setAttribute("error", "ID must be bigger than 0 !");
            } else if (checkId == 4) {
                request.setAttribute("error", "Not gallery found !");

            } else {
                //validate page number
                int numberPage = gdao.getNumberPageImage(id, numberImageInAPage);
                // check page
                int checkPage = 1;
                int page = 1;
                if (request.getParameter("page") == null) {
                    page = 1;
                } else {
                    String spage = request.getParameter("page");
                    try {
                        page = Integer.parseInt(spage);
                    } catch (Exception e) {
                        checkPage = 2;
                    }
                }
                if (page > numberPage) {
                    checkPage = 3;
                }
                if (page < 1) {
                    checkPage = 4;
                }
                if (checkPage == 2) {
                    request.setAttribute("error", "Page number must be integer number !");
                } else if (checkPage == 4) {
                    request.setAttribute("error", "Page number must be bigger than 0 !");
                } else if (checkPage == 3) {
                    request.setAttribute("error", "Page number bigger than numbers of page !");
                } else {
                    //validate image number
                    Gallery ga = gdao.getGalleryById(id, numberImageInAPage, page);
                    ArrayList<Image> listImage = ga.getListImage();
                    if (ga.getContent() == null) {
                        request.setAttribute("error", "Not gallery found");
                    } else {
                        request.setAttribute("galleryDetail", ga);
                        request.setAttribute("listImage", listImage);
                        int image = 0;
                        int checkImage = 1;
                        if (request.getParameter("image") == null) {
                            image = 0;
                        } else {
                            String sImage = request.getParameter("image");
                            try {
                                image = Integer.parseInt(sImage);
                            } catch (Exception e) {
                                checkImage = 2;
                            }
                        }
                        if (image >= listImage.size()) {
                            checkImage = 3;
                        }
                        if (image < 0) {
                            checkImage = 4;
                        }
                        if (checkImage == 2) {
                            request.setAttribute("error", "Image number must be integer number !");
                        } else if (checkImage == 3) {
                            request.setAttribute("error", "Image not found !");
                        } else if (checkImage == 4) {
                            request.setAttribute("error", "Image number must be bigger than 0 !");
                        } else {
                            request.setAttribute("mainImage", listImage.get(image).getSource());
                            request.setAttribute("page", page);
                            request.setAttribute("numberPage", numberPage);
                        }
                    }
                }
            }
            request.setAttribute("linkActive", id);
            request.getRequestDispatcher("detail.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
