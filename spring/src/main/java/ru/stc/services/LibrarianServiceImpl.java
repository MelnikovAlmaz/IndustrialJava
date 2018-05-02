package ru.stc.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.stc.controllers.listeners.AppStartListener;
import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.model.dao.impl.LibrarianDao;
import ru.stc.model.dao.interfaces.LibrarianDAO;
import ru.stc.model.pojo.LibrarianBean;

@Service
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
