import org.apache.log4j.Logger;
import org.junit.*;
import ru.stc.controllers.listeners.AppStartListener;
import ru.stc.exceptions.DatabaseConnectionException;
import ru.stc.exceptions.EmptyResultException;
import ru.stc.exceptions.InvalidDataSchemeFormat;
import ru.stc.exceptions.UnsuccessfulExequtionException;
import ru.stc.model.dao.impl.BookDao;
import ru.stc.model.dao.impl.IssueDao;
import ru.stc.model.pojo.BookBean;
import ru.stc.model.pojo.IssueBookBean;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOTest {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    private static BookBean book;
    private static BookDao bookDao;
    private static IssueDao issueDao;
    private static IssueBookBean issueBook;

    @BeforeClass
    public static void init(){
        book = new BookBean("9999", "Test book", "Author", "Publisher", 100);
        bookDao = new BookDao();
        issueDao = new IssueDao();
        issueBook = new IssueBookBean("9999", 1, "Test boy", 1);
    }

    @Test
    public void bookDAOTest() throws DatabaseConnectionException, InvalidDataSchemeFormat, UnsuccessfulExequtionException, EmptyResultException {
        // Check insert method
        assert (bookDao.insert(book).equals("9999"));
        // Check returning all books
        assert (bookDao.getAll().size() > 0);
        // Check returning book by id
        assert (bookDao.getById("9999").getName().equals("Test book"));

        // Check delete book
        assert (bookDao.delete(book) == 1);
    }

    @Test
    public void issueBookDAOTest() throws DatabaseConnectionException, InvalidDataSchemeFormat, UnsuccessfulExequtionException, EmptyResultException {
        // Prepare environment
        bookDao.insert(book);

        // Check issuing the book
        issueDao.insert(issueBook);
        assert (bookDao.getById("9999").getIssued() == 1);
        // Check returning all issues
        assert (issueDao.getAll().size() > 0);

        issueDao.returnBook(issueBook);
        assert (bookDao.getById("9999").getIssued() == 0);
        bookDao.delete(book);
    }
}
