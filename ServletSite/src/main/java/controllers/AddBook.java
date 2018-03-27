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

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addbookform.jsp").include(request, response);
		LOGGER.info("[GET] /AddBook requested");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String callno = request.getParameter("callno");
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String squantity = request.getParameter("quantity");
            int quantity = Integer.parseInt(squantity);
            if (quantity <= 0) {
                LOGGER.info("[POST] /AddBook quantity parameter should be greater than 0");
            }
            else {
                BookBean bean = new BookBean(callno, name, author, publisher, quantity);
                new BookBeanServiceImpl().insert(bean);
            }
        }
        finally {
            response.sendRedirect("/1/ViewBook");
            LOGGER.info("[POST] /AddBook requested");
        }
	}

}
