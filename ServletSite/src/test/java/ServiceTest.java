import controllers.listeners.AppStartListener;
import model.pojo.BookBean;
import model.pojo.IssueBookBean;
import model.services.BookBeanServiceImpl;
import model.services.IssueServiceImpl;
import model.utils.exceptions.DatabaseConnectionException;
import model.utils.exceptions.EmptyResultException;
import model.utils.exceptions.InvalidDataSchemeFormat;
import model.utils.exceptions.UnsuccessfulExequtionException;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServiceTest {
    private static final Logger LOGGER = Logger.getLogger(AppStartListener.class);
    private static BookBean book;
    private static BookBeanServiceImpl bookBeanService;
    private static IssueServiceImpl issueService;
    private static IssueBookBean issueBook;

    @BeforeClass
    public static void init(){
        book = new BookBean("9999", "Test book", "Author", "Publisher", 100);
        bookBeanService = new BookBeanServiceImpl();
        issueService = new IssueServiceImpl();
        issueBook = new IssueBookBean("9999", 1, "Test boy", 1);
    }

    @Test
    public void bookDAOTest() throws DatabaseConnectionException, InvalidDataSchemeFormat, UnsuccessfulExequtionException, EmptyResultException {
        // Check insert method
        assert (bookBeanService.insert(book).equals("9999"));
        // Check returning all books
        assert (bookBeanService.getAll().size() > 0);
        // Check returning book by id
        assert (bookBeanService.getById("9999").getName().equals("Test book"));

        // Check delete book
        assert (bookBeanService.delete(book) == 1);
    }

    @Test
    public void issueBookDAOTest() throws DatabaseConnectionException, InvalidDataSchemeFormat, UnsuccessfulExequtionException, EmptyResultException {
        // Prepare environment
        bookBeanService.insert(book);

        // Check issuing the book
        issueService.insert(issueBook);
        assert (bookBeanService.getById("9999").getIssued() == 1);
        // Check returning all issues
        assert (issueService.getAll().size() > 0);

        issueService.returnBook(issueBook);
        assert (bookBeanService.getById("9999").getIssued() == 0);
        bookBeanService.delete(book);
    }
}
