package controllers;


import model.dao.impl.LibrarianDao;
import model.pojo.LibrarianBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewLibrarian")
public class ViewLibrarian extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LibrarianBean> list = new LibrarianDao().getAll();
        request.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/listLibrarian.jsp").forward(request, response);
    }
}
