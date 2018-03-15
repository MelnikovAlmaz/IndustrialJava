package controllers;


import model.dao.impl.BookDao;
import model.pojo.BookBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookBean> list = new BookDao().getAll();
        request.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/listBook.jsp").forward(request, response);
    }
}
