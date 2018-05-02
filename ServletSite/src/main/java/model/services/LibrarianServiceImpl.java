package model.services;

import controllers.listeners.AppStartListener;
import model.dao.impl.LibrarianDao;
import model.dao.interfaces.LibrarianDAO;
import model.pojo.LibrarianBean;
import model.utils.exceptions.DatabaseConnectionException;
import org.apache.log4j.Logger;


public class LibrarianServiceImpl implements LibrarianService<LibrarianBean, Integer> {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    private final LibrarianDAO librarianDAO;

    public LibrarianServiceImpl() {
        this.librarianDAO = new LibrarianDao();
    }

    @Override
    public boolean authenticate(String email, String password) {
        try {
            return librarianDAO.authenticate(email, password);
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return false;
        }
    }
}
