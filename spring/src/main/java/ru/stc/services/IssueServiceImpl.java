package ru.stc.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stc.controllers.listeners.AppStartListener;
import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.exceptions.InvalidDataSchemeFormat;
import ru.stc.exceptions.UnsuccessfulExequtionException;
import ru.stc.model.dao.impl.IssueDao;
import ru.stc.model.dao.interfaces.IssueDAO;
import ru.stc.model.pojo.IssueBookBean;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService<IssueBookBean, String> {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    @Autowired
    private IssueDAO issueDAO;

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
    public int returnBook(IssueBookBean entity) {
        issueDAO.returnBook(entity);
        return 1;
    }
}
