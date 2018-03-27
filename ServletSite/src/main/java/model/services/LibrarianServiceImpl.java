package model.services;

import model.dao.impl.LibrarianDao;
import model.dao.interfaces.LibrarianDAO;
import model.pojo.LibrarianBean;


public class LibrarianServiceImpl implements LibrarianService<LibrarianBean, Integer> {
    private final LibrarianDAO librarianDAO;

    public LibrarianServiceImpl() {
        this.librarianDAO = new LibrarianDao();
    }

    @Override
    public boolean authenticate(String email, String password) {
        return librarianDAO.authenticate(email, password);
    }
}
