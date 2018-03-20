package ru.stc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stc.model.dao.interfaces.IssueDAO;
import ru.stc.model.pojo.IssueBookBean;
import ru.stc.model.pojo.IssueBookBean;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService<IssueBookBean, String> {
    private final IssueDAO issueDAO;

    @Autowired
    public IssueServiceImpl(IssueDAO issueDAO) {
        this.issueDAO = issueDAO;
    }

    @Override
    public IssueBookBean getById(String id) {
        return issueDAO.getById(id);
    }

    @Override
    public List<IssueBookBean> getAll() {
        return issueDAO.getAll();
    }

    @Override
    public IssueBookBean save(IssueBookBean entity) {
        return issueDAO.save(entity);
    }

    @Override
    public String insert(IssueBookBean entity) {
        return issueDAO.insert(entity);
    }

    @Override
    public int update(IssueBookBean entity) {
        issueDAO.update(entity);
        return 1;
    }

    @Override
    public int delete(IssueBookBean entity) {
        issueDAO.delete(entity);
        return 1;
    }

    @Override
    public int returnBook(IssueBookBean entity) {
        issueDAO.returnBook(entity);
        return 1;
    }
}
