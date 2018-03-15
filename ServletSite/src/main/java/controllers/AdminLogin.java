package controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email.equals("admin@admin.ru") && password.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", "admin");

            getServletContext().getRequestDispatcher("/adminMain.jsp").forward(request, response);

        } else {
            response.sendRedirect("/1");
        }
    }

}
