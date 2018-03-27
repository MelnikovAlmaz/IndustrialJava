package model.dao.interfaces;

import model.pojo.IssueBookBean;

public interface IssueDAO extends DAO<IssueBookBean, String> {
    void returnBook(IssueBookBean entity);
}
