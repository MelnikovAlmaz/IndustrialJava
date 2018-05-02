package ru.stc.exceptions;

import java.sql.SQLException;

public class DatabaseConnectionException extends SQLException {
    public DatabaseConnectionException(SQLException e) {
        super(e);
    }
}
