package controllers;

import controllers.listeners.AppStartListener;
import model.dao.impl.BookDao;
import model.pojo.BookBean;
import model.services.BookBeanService;
import model.services.BookBeanServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String callno = request.getParameter("callno");
            BookBeanService bookBeanService = new BookBeanServiceImpl();
            BookBean book = bookBeanService.getById(callno);
            bookBeanService.delete(book);
        } finally {
            response.sendRedirect("ViewBook");
            LOGGER.info("[GET] /DeleteBook requested");
        }
    }
}
