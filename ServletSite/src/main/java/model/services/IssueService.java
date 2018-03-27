package model.services;

import java.util.List;


public interface IssueService<E, PK> {
    E getById(PK id);

    List<E> getAll();

    E save(E entity);

    PK insert(E entity);

    int update(E entity);

    int delete(E entity);

    int returnBook(E entity);
}
