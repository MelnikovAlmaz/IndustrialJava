package model.services;

import controllers.listeners.AppStartListener;
import model.dao.impl.IssueDao;
import model.dao.interfaces.IssueDAO;
import model.pojo.IssueBookBean;
import model.utils.exceptions.DatabaseConnectionException;
import model.utils.exceptions.EmptyResultException;
import model.utils.exceptions.InvalidDataSchemeFormat;
import model.utils.exceptions.UnsuccessfulExequtionException;
import org.apache.log4j.Logger;

import java.util.List;

public class IssueServiceImpl implements IssueService<IssueBookBean, String> {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    private final IssueDAO issueDAO;

    public IssueServiceImpl() {
        this.issueDAO = new IssueDao();
    }

    @Override
    public IssueBookBean getById(String id) {
        try {
            return issueDAO.getById(id);
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return null;
        } catch (EmptyResultException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". IssueDAO.getById() returns empty result!");
            return null;
        } catch (InvalidDataSchemeFormat throwables) {
            LOGGER.warn("Class - " + getClass().getName() + ". IssueBook table has invalid scheme!");
            return null;
        }
    }

    @Override
    public List<IssueBookBean> getAll() {
        try {
            return issueDAO.getAll();
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return null;
        } catch (InvalidDataSchemeFormat throwables) {
            LOGGER.warn("Class - " + getClass().getName() + ". IssueBook table has invalid scheme!");
            return null;
        }
    }

    @Override
    public IssueBookBean save(IssueBookBean entity) {
        return issueDAO.save(entity);
    }

    @Override
    public String insert(IssueBookBean entity) {
        try {
            return issueDAO.insert(entity);
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return null;
        } catch (UnsuccessfulExequtionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". insert(IssueBookBean entity) unsuccessful execution!");
            return null;
        }
    }

    @Override
    public int update(IssueBookBean entity) {
        issueDAO.update(entity);
        return 1;
    }

    @Override
    public int delete(IssueBookBean entity) {
        try {
            issueDAO.delete(entity);
            return 1;
        } catch (DatabaseConnectionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". Database connection error!");
            return 0;
        } catch (UnsuccessfulExequtionException e) {
            LOGGER.warn("Class - " + getClass().getName() + ". delete(IssueBookBean entity) unsuccessful execution!");
            return 0;
        }
    }

    @Override
    public int returnBook(IssueBookBean entity) {
        issueDAO.returnBook(entity);
        return 1;
    }
}
