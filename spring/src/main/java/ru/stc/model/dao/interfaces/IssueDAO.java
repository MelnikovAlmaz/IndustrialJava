package ru.stc.model.dao.interfaces;

import ru.stc.model.pojo.IssueBookBean;

public interface IssueDAO extends DAO<IssueBookBean, String> {
    void returnBook(IssueBookBean entity);
}
