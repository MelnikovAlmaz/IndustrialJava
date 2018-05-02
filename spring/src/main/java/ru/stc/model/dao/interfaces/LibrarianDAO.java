package ru.stc.model.dao.interfaces;

import ru.stc.exceptions.DatabaseConnectionException;

public interface LibrarianDAO {
    boolean authenticate(String email, String password) throws DatabaseConnectionException;
}
