package ru.stc.services;

import java.util.List;


public interface IssueService<E, PK> {

    List<E> getAll();

    PK insert(E entity);

    int returnBook(E entity);
}
