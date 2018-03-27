package controllers;


import controllers.listeners.AppStartListener;
import model.dao.impl.BookDao;
import model.dao.impl.IssueDao;
import model.pojo.IssueBookBean;
import model.services.IssueService;
import model.services.IssueServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<IssueBookBean> list = new IssueServiceImpl().getAll();
        request.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/listIssuedBook.jsp").forward(request, response);
        LOGGER.info("[GET] /ViewIssuedBook requested");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        IssueService<IssueBookBean, String> issueService = new IssueServiceImpl();
        IssueBookBean issueBookBean = issueService.getById(id);
        int i = new IssueServiceImpl().returnBook(issueBookBean);
        response.sendRedirect("/1/ViewIssuedBook");
        LOGGER.info("[POST] /ViewIssuedBook requested");
    }
}
