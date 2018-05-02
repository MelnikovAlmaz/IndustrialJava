package model.dao.interfaces;

import model.utils.exceptions.DatabaseConnectionException;
import model.utils.exceptions.EmptyResultException;
import model.utils.exceptions.InvalidDataSchemeFormat;
import model.utils.exceptions.UnsuccessfulExequtionException;

import java.util.Collection;
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

    int delete(E entity) throws DatabaseConnectionException, UnsuccessfulExequtionException;
}