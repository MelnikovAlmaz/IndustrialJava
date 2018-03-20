package ru.stc.model.dao.impl;


import org.springframework.stereotype.Repository;
import ru.stc.model.dao.interfaces.LibrarianDAO;
import ru.stc.model.pojo.BookBean;
import ru.stc.model.pojo.LibrarianBean;
import ru.stc.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LibrarianDao implements LibrarianDAO {

    @Override
    public LibrarianBean getById(Integer id) {
        LibrarianBean bean = new LibrarianBean();
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from e_librarian where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setMobile(rs.getLong(5));
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return bean;
    }

    @Override
    public List<LibrarianBean> getAll() {
        List<LibrarianBean> list = new ArrayList<LibrarianBean>();
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from e_librarian");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LibrarianBean bean = new LibrarianBean();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setMobile(rs.getLong("mobile"));
                list.add(bean);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    @Override
    public LibrarianBean save(LibrarianBean entity) {
        return null;
    }

    @Override
    public Integer insert(LibrarianBean bean) {
        int status = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("insert into e_librarian(name,email,password,mobile) values(?,?,?,?)");
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getPassword());
            ps.setLong(4, bean.getMobile());
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }

    @Override
    public int update(LibrarianBean bean) {
        int status = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where id=?");
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getPassword());
            ps.setLong(4, bean.getMobile());
            ps.setInt(5, bean.getId());
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }


    @Override
    public int delete(LibrarianBean entity) {
        int status = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("delete from e_librarian where id=?");
            ps.setInt(1, entity.getId());
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }

    @Override
    public boolean authenticate(String email, String password) {
        boolean status = false;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from e_librarian where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }
}
