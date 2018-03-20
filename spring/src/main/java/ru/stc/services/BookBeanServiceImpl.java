package ru.stc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stc.model.dao.interfaces.BookDAO;
import ru.stc.model.pojo.BookBean;

import java.util.List;

@Service
public class BookBeanServiceImpl implements BookBeanService {
    private final BookDAO bookDAO;

    @Autowired
    public BookBeanServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public BookBean getById(String id) {
        return bookDAO.getById(id);
    }

    @Override
    public List<BookBean> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public BookBean save(BookBean entity) {
        return bookDAO.save(entity);
    }

    @Override
    public String insert(BookBean entity) {
        return bookDAO.insert(entity);
    }

    @Override
    public int update(BookBean entity) {
        bookDAO.update(entity);
        return 1;
    }

    @Override
    public int delete(BookBean entity) {
        bookDAO.delete(entity);
        return 1;
    }
}
