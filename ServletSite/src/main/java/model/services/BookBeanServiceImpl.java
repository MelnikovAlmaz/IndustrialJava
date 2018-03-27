package model.services;

import model.dao.impl.BookDao;
import model.dao.interfaces.BookDAO;
import model.pojo.BookBean;

import java.util.List;

public class BookBeanServiceImpl implements BookBeanService {
    private final BookDAO bookDAO;

    public BookBeanServiceImpl() {
        this.bookDAO = new BookDao();
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
