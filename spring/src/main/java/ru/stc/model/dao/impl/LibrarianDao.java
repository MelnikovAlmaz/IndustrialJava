package ru.stc.model.dao.impl;

import org.springframework.stereotype.Repository;
import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.model.dao.interfaces.LibrarianDAO;
import ru.stc.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LibrarianDao implements LibrarianDAO {
    public boolean authenticate(String email, String password) throws DatabaseConnectionException {
        boolean status = false;
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from e_librarian where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
        return status;
    }
}
