package ru.stc.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.stc.controllers.listeners.AppStartListener;
import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.exceptions.EmptyResultException;
import ru.stc.exceptions.InvalidDataSchemeFormat;
import ru.stc.exceptions.UnsuccessfulExequtionException;
import ru.stc.model.dao.impl.BookDao;
import ru.stc.model.dao.interfaces.BookDAO;
import ru.stc.model.pojo.BookBean;

import java.util.List;

@Service
public class BookBeanServiceImpl implements BookBeanService {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    private final BookDAO bookDAO;

    public BookBeanServiceImpl() {
        this.bookDAO = new BookDao();
    }

    @Override
    public BookBean getById(String id) {
        try {
            return bookDAO.getById(id);
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return null;
        } catch (EmptyResultException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". BookDAO.getById() returns empty result!");
            return null;
        } catch (InvalidDataSchemeFormat throwables) {
            LOGGER.warn("Class - " + getClass().getName() + ". Book table has invalid scheme!");
            return null;
        }
    }

    @Override
    public List<BookBean> getAll() {
        try {
            return bookDAO.getAll();
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return null;
        } catch (InvalidDataSchemeFormat throwables) {
            LOGGER.warn("Class - " + getClass().getName() + ". Book table has invalid scheme!");
            return null;
        }
    }

    @Override
    public String insert(BookBean entity) {
        try {
            return bookDAO.insert(entity);
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return null;
        } catch (UnsuccessfulExequtionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". insert(BookBean entity) unsuccessful execution!");
            return null;
        }
    }

    @Override
    public int delete(BookBean entity) {
        try {
            bookDAO.delete(entity);
            return 1;
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return 0;
        }
    }
}
