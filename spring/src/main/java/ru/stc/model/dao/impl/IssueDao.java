package ru.stc.model.dao.impl;

import org.springframework.stereotype.Repository;
import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.exceptions.InvalidDataSchemeFormat;
import ru.stc.exceptions.UnsuccessfulExequtionException;
import ru.stc.model.dao.interfaces.IssueDAO;
import ru.stc.model.pojo.IssueBookBean;
import ru.stc.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.stc.model.dao.impl.BookDao.getIssuedById;

@Repository
public class IssueDao implements IssueDAO {
    @Override
    public IssueBookBean getById(String id) {
        return null;
    }

    @Override
    public List<IssueBookBean> getAll() throws DatabaseConnectionException, InvalidDataSchemeFormat {
        List<IssueBookBean> list = new ArrayList<IssueBookBean>();
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from e_issuebook order by issueddate desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IssueBookBean bean = new IssueBookBean();
                try {
                    bean.setCallno(rs.getString("callno"));
                    bean.setStudentid(rs.getInt("studentid"));
                    bean.setStudentname(rs.getString("studentname"));
                    bean.setStudentmobile(rs.getLong("studentmobile"));
                    bean.setIssueddate(rs.getDate("issueddate"));
                    bean.setReturnstatus(rs.getString("returnstatus"));
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
    public IssueBookBean save(IssueBookBean entity) {
        return null;
    }

    @Override
    public String insert(IssueBookBean bean) throws DatabaseConnectionException, UnsuccessfulExequtionException {
        String callno = bean.getCallno();
        if (checkIssue(callno)) {
            int status = 0;
            try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
                PreparedStatement ps = con.prepareStatement("insert into e_issuebook (callno, studentid, studentname, studentmobile, issueddate, returnstatus) values(?,?,?,?,?,?)");
                ps.setString(1, bean.getCallno());
                ps.setInt(2, bean.getStudentid());
                ps.setString(3, bean.getStudentname());
                ps.setLong(4, bean.getStudentmobile());
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                ps.setDate(5, currentDate);
                ps.setString(6, "no");

                status = ps.executeUpdate();
                if (status > 0) {
                    PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
                    ps2.setInt(1, getIssuedById(callno) + 1);
                    ps2.setString(2, callno);
                    status = ps2.executeUpdate();
                } else {
                    throw  new UnsuccessfulExequtionException();
                }
            } catch (SQLException e) {
                throw new DatabaseConnectionException(e);
            }

            return callno;
        } else {
            return "";
        }
    }

    @Override
    public int update(IssueBookBean entity) {
        return 0;
    }

    @Override
    public int delete(IssueBookBean entity) {
        return 0;
    }

    public static boolean checkIssue(String callno) throws DatabaseConnectionException {
        boolean status = false;
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=? and quantity>issued");
            ps.setString(1, callno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }

        return status;
    }

    @Override
    public void returnBook(IssueBookBean entity) {
        int status = 0;
        try (Connection con = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
            ps.setString(1, entity.getCallno());
            ps.setInt(2, entity.getStudentid());

            status = ps.executeUpdate();
            if (status > 0) {
                PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
                ps2.setInt(1, getIssuedById(entity.getCallno()) - 1);
                ps2.setString(2, entity.getCallno());
                status = ps2.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
