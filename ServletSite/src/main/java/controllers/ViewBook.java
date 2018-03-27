package controllers;


import controllers.listeners.AppStartListener;
import model.dao.impl.BookDao;
import model.pojo.BookBean;
import model.services.BookBeanServiceImpl;
import org.apache.log4j.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookBean> list = new BookBeanServiceImpl().getAll();
        request.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/listBook.jsp").forward(request, response);
        LOGGER.info("[GET] /ViewBook requested");
    }
}
