package model.dao.interfaces;

import model.utils.exceptions.DatabaseConnectionException;

public interface LibrarianDAO {
    boolean authenticate(String email, String password) throws DatabaseConnectionException;
}
