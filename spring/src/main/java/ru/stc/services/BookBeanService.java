package ru.stc.services;

import ru.stc.model.pojo.BookBean;

import java.util.List;

public interface BookBeanService {
    BookBean getById(String id);

    List<BookBean> getAll();

    String insert(BookBean entity);

    int delete(BookBean entity);
}
