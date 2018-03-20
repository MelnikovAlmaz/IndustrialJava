package ru.stc.services;

import ru.stc.model.pojo.BookBean;

import java.util.List;

public interface BookBeanService {
    BookBean getById(String id);

    List<BookBean> getAll();

    BookBean save(BookBean entity);

    String insert(BookBean entity);

    int update(BookBean entity);

    int delete(BookBean entity);
}
