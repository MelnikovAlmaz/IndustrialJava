package model.dao.impl;

import model.dao.interfaces.LibrarianDAO;
import model.pojo.LibrarianBean;
import model.utils.DataSourceFactory;
import model.utils.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
