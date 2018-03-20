package ru.stc.model.dao.impl;

import org.springframework.stereotype.Repository;
import ru.stc.model.dao.interfaces.BookDAO;
import ru.stc.model.pojo.BookBean;
import ru.stc.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao implements BookDAO {
    @Override
    public BookBean getById(String id) {
        try {
            BookBean bean = new BookBean();
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                bean.setCallno(rs.getString("callno"));
                bean.setName(rs.getString("name"));
                bean.setAuthor(rs.getString("author"));
                bean.setPublisher(rs.getString("publisher"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setIssued(rs.getInt("issued"));

            }
            con.close();
            return bean;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<BookBean> getAll() {
        List<BookBean> list = new ArrayList<BookBean>();
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from e_book");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookBean bean = new BookBean();
                bean.setCallno(rs.getString("callno"));
                bean.setName(rs.getString("name"));
                bean.setAuthor(rs.getString("author"));
                bean.setPublisher(rs.getString("publisher"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setIssued(rs.getInt("issued"));

                list.add(bean);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    @Override
    public BookBean save(BookBean entity) {
        return null;
    }

    @Override
    public String insert(BookBean bean) {
        int status = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("insert into e_book values(?,?,?,?,?,?)");
            ps.setString(1, bean.getCallno());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getAuthor());
            ps.setString(4, bean.getPublisher());
            ps.setInt(5, bean.getQuantity());
            ps.setInt(6, 0);
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return bean.getCallno();
    }

    @Override
    public int update(BookBean bean) {
        return 0;
    }

    @Override
    public int delete(BookBean entity) {
        int status = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("delete from e_book where callno=?");
            ps.setString(1, entity.getCallno());
            status = ps.execute() ? 1 : 0;
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }


    public static int getIssuedById(String callno) {
        int issued = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1, callno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                issued = rs.getInt("issued");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return issued;
    }


    public static int returnBook(String callno, int studentid) {
        int status = 0;
        try {
            Connection con = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
            ps.setString(1, callno);
            ps.setInt(2, studentid);

            status = ps.executeUpdate();
            if (status > 0) {
                PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
                ps2.setInt(1, getIssuedById(callno) - 1);
                ps2.setString(2, callno);
                status = ps2.executeUpdate();
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }
}
