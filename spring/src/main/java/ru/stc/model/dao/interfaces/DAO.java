package ru.stc.model.dao.interfaces;

import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.exceptions.EmptyResultException;
import ru.stc.exceptions.InvalidDataSchemeFormat;
import ru.stc.exceptions.UnsuccessfulExequtionException;

import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public interface DAO<E, PK> {

    E getById(PK id) throws DatabaseConnectionException, EmptyResultException, InvalidDataSchemeFormat;

    List<E> getAll() throws DatabaseConnectionException, InvalidDataSchemeFormat;

    E save(E entity);

    PK insert(E entity) throws DatabaseConnectionException, UnsuccessfulExequtionException;

    int update(E entity);

    int delete(E entity) throws DatabaseConnectionException;
}