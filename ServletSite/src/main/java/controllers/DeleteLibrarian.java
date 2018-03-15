package controllers;

import model.dao.impl.LibrarianDao;
import model.pojo.LibrarianBean;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteLibrarian")
public class DeleteLibrarian extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		LibrarianBean librarianBean = new LibrarianBean();
		librarianBean.setId(id);
		new LibrarianDao().delete(librarianBean);
		response.sendRedirect("ViewLibrarian");
	}
}
