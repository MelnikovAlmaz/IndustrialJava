package controllers;


import controllers.listeners.AppStartListener;
import model.dao.impl.LibrarianDao;
import model.services.LibrarianServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (new LibrarianServiceImpl().authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", email);

            request.getRequestDispatcher("/librarianMain.jsp").include(request, response);
            LOGGER.info("[POST] /LibrarianLogin " + email + " was logged in");
        } else {
            response.sendRedirect("/1");
            LOGGER.info("[POST] /LibrarianLogin invalid username and password");
        }
        LOGGER.info("[POST] /LibrarianLogin requested");
    }

}
