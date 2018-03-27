package controllers;


import controllers.listeners.AppStartListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogoutLibrarian")
public class LogoutLibrarian extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
        LOGGER.info("[GET] /LibrarianLogout requested");
    }
}
