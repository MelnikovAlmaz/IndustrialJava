package controllers;


import model.dao.impl.LibrarianDao;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (new LibrarianDao().authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", email);

            request.getRequestDispatcher("/librarianMain.jsp").include(request, response);

        } else {
            response.sendRedirect("/1");
        }
    }

}
