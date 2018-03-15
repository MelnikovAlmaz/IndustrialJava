package controllers;

import model.dao.impl.BookDao;
import model.pojo.BookBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBean book = new BookBean();
        book.setCallno(request.getParameter("callno"));
        new BookDao().delete(book);
        response.sendRedirect("ViewBook");
    }
}
