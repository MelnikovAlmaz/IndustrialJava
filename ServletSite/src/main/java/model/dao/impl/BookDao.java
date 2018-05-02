package model.dao.impl;

import model.dao.interfaces.BookDAO;
import model.pojo.BookBean;
import model.utils.DataSourceFactory;
import model.utils.exceptions.DatabaseConnectionException;
import model.utils.exceptions.EmptyResultException;
import model.utils.exceptions.InvalidDataSchemeFormat;
import model.utils.exceptions.UnsuccessfulExequtionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements BookDAO {
    @Override
    public BookBean getById(String id) throws DatabaseConnectionException, EmptyResultException, InvalidDataSchemeFormat {
        BookBean bean = new BookBean();
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            // Empty result
            if (rs.getFetchSize() == 0){
                throw new EmptyResultException();
            }

            if (rs.next()) {
                try{
                    bean.setCallno(rs.getString("callno"));
                    bean.setName(rs.getString("name"));
                    bean.setAuthor(rs.getString("author"));
                    bean.setPublisher(rs.getString("publisher"));
                    bean.setQuantity(rs.getInt("quantity"));
                    bean.setIssued(rs.getInt("issued"));
                }
                catch (SQLException e){
                    throw new InvalidDataSchemeFormat(e);
                }
            }
            return bean;
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public List<BookBean> getAll() throws DatabaseConnectionException, InvalidDataSchemeFormat {
        List<BookBean> list = new ArrayList<BookBean>();
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from e_book");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookBean bean = new BookBean();
                try{
                    bean.setCallno(rs.getString("callno"));
                    bean.setName(rs.getString("name"));
                    bean.setAuthor(rs.getString("author"));
                    bean.setPublisher(rs.getString("publisher"));
                    bean.setQuantity(rs.getInt("quantity"));
                    bean.setIssued(rs.getInt("issued"));
                }
                catch (SQLException e){
                    throw new InvalidDataSchemeFormat(e);
                }

                list.add(bean);
            }

            return list;
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public BookBean save(BookBean entity) {
        return null;
    }

    @Override
    public String insert(BookBean bean) throws DatabaseConnectionException, UnsuccessfulExequtionException {
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into e_book values(?,?,?,?,?,?)");
            ps.setString(1, bean.getCallno());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getAuthor());
            ps.setString(4, bean.getPublisher());
            ps.setInt(5, bean.getQuantity());
            ps.setInt(6, 0);
            if(ps.executeUpdate() == 1){
                return bean.getCallno();
            } else {
                throw new UnsuccessfulExequtionException();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public int update(BookBean bean) {
        return 0;
    }

    @Override
    public int delete(BookBean entity) throws DatabaseConnectionException, UnsuccessfulExequtionException {
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from e_book where callno=?");
            ps.setString(1, entity.getCallno());
            if (ps.execute()){
                return 1;
            }
            else {
                throw new UnsuccessfulExequtionException();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }


    public static int getIssuedById(String callno) throws DatabaseConnectionException, EmptyResultException {
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1, callno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("issued");
            } else {
                throw new EmptyResultException();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }
}