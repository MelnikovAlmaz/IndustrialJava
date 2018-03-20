package ru.stc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stc.model.dao.interfaces.BookDAO;
import ru.stc.model.dao.interfaces.LibrarianDAO;
import ru.stc.model.pojo.LibrarianBean;

import java.util.List;

@Service
public class LibrarianServiceImpl implements LibrarianService<LibrarianBean, Integer> {
    private final LibrarianDAO librarianDAO;

    @Autowired
    public LibrarianServiceImpl(LibrarianDAO librarianDAO) {
        this.librarianDAO = librarianDAO;
    }

    @Override
    public LibrarianBean getById(Integer id) {
        return librarianDAO.getById(id);
    }

    @Override
    public List<LibrarianBean> getAll() {
        return librarianDAO.getAll();
    }

    @Override
    public LibrarianBean save(LibrarianBean entity) {
        return librarianDAO.save(entity);
    }

    @Override
    public Integer insert(LibrarianBean entity) {
        return librarianDAO.insert(entity);
    }

    @Override
    public int update(LibrarianBean entity) {
        librarianDAO.update(entity);
        return 1;
    }

    @Override
    public int delete(LibrarianBean entity) {
        librarianDAO.delete(entity);
        return 1;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return librarianDAO.authenticate(email, password);
    }
}
