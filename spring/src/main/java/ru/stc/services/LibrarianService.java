package ru.stc.services;

import java.util.List;

public interface LibrarianService<E, PK> {
    E getById(PK id);

    List<E> getAll();

    E save(E entity);

    PK insert(E entity);

    int update(E entity);

    int delete(E entity);

    boolean authenticate(String email, String password);
}
