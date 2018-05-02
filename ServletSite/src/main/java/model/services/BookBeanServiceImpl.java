package model.services;

import controllers.listeners.AppStartListener;
import model.dao.impl.BookDao;
import model.dao.interfaces.BookDAO;
import model.pojo.BookBean;
import model.utils.exceptions.DatabaseConnectionException;
import model.utils.exceptions.EmptyResultException;
import model.utils.exceptions.InvalidDataSchemeFormat;
import model.utils.exceptions.UnsuccessfulExequtionException;
import org.apache.log4j.Logger;

import java.util.List;

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
    public BookBean save(BookBean entity) {
        return bookDAO.save(entity);
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
    public int update(BookBean entity) {
        bookDAO.update(entity);
        return 1;
    }

    @Override
    public int delete(BookBean entity) {
        try {
            bookDAO.delete(entity);
            return 1;
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return 0;
        } catch (UnsuccessfulExequtionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". delete(BookBean entity) unsuccessful execution!");
            return 0;
        }
    }
}
