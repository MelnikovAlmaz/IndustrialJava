package ru.stc.model.dao.interfaces;

import org.springframework.stereotype.Repository;
import ru.stc.model.pojo.LibrarianBean;

@Repository
public interface LibrarianDAO extends DAO<LibrarianBean, Integer> {
    boolean authenticate(String email, String password);
}
