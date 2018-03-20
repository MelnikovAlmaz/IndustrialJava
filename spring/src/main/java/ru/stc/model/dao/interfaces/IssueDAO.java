package ru.stc.model.dao.interfaces;

import org.springframework.stereotype.Repository;
import ru.stc.model.pojo.IssueBookBean;

@Repository
public interface IssueDAO extends DAO<IssueBookBean, String> {
    void returnBook(IssueBookBean entity);
}
