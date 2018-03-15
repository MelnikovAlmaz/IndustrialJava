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

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("issuebookform.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String callno = request.getParameter("callno");
        Integer studentid = Integer.parseInt(request.getParameter("studentid"));
        String studentname = request.getParameter("studentname");
        String sstudentmobile = request.getParameter("studentmobile");
        long studentmobile = Long.parseLong(sstudentmobile);

        IssueBookBean bean = new IssueBookBean(callno, studentid, studentname, studentmobile);
        String i = new IssueDao().insert(bean);
        response.sendRedirect("/1/ViewIssuedBook");
    }

}
