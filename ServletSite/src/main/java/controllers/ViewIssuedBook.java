package controllers;


import model.dao.impl.BookDao;
import model.dao.impl.IssueDao;
import model.pojo.IssueBookBean;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<IssueBookBean> list = new IssueDao().getAll();
        request.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/listIssuedBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String callno = request.getParameter("callno");
        String sstudentid = request.getParameter("studentid");
        int studentid = Integer.parseInt(sstudentid);

        int i = BookDao.returnBook(callno, studentid);
        response.sendRedirect("/1/ViewIssuedBook");
    }
}
