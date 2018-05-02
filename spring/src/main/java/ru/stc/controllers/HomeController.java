package ru.stc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.stc.model.pojo.BookBean;
import ru.stc.model.pojo.IssueBookBean;
import ru.stc.services.BookBeanService;
import ru.stc.services.IssueService;
import ru.stc.services.LibrarianService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private final LibrarianService librarianService;
    @Autowired
    private final BookBeanService bookBeanService;
    @Autowired
    private final IssueService issueService;

    @Autowired
    public HomeController(LibrarianService librarianService, BookBeanService bookBeanService, IssueService issueService) {
        this.librarianService = librarianService;
        this.bookBeanService = bookBeanService;
        this.issueService = issueService;
    }

    @GetMapping("/AddBook")
    public String getAddBook(Model model, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            model.addAttribute("book", new BookBean());
            return "addbookform";
        }
        else {
            return "index";
        }
    }

    @PostMapping("/AddBook")
    public ModelAndView postAddBook(@ModelAttribute BookBean book, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            ModelAndView viewBook = new ModelAndView("listBook");
            bookBeanService.insert(book);
            List books = bookBeanService.getAll();
            viewBook.addObject("list", books);
            return viewBook;
        }
        else {
            ModelAndView viewBook = new ModelAndView("index");
            return viewBook;
        }
    }

    @GetMapping("/")
    public String getHome(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == "admin") {
            return "adminMain";
        }
        else if (session.getAttribute("userLogin") != "") {
            return "librarianMain";
        }
        else {
            return "index";
        }
    }

    @PostMapping("/")
    public String postHome(Model model, HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (librarianService.authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", email);
            return "librarianMain";
        }
        else if (email.equals("admin@admin.ru") && password.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", email);
            return "adminMain";
        }
        else {
            return "index";
        }
    }

    @GetMapping("/ViewBook")
    public String getViewBook(Model model, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            List<BookBean> list = bookBeanService.getAll();
            model.addAttribute("list", list);
            return "listBook";
        }
        else {
            return "index";
        }
    }

    @GetMapping("/ViewIssueBook")
    public String getListIssueBook(Model model, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            List<IssueBookBean> list = issueService.getAll();
            model.addAttribute("list", list);
            return "listIssuedBook";
        }
        else {
            return "index";
        }
    }

    @GetMapping("/IssueBook")
    public String getIssueBook(Model model, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            model.addAttribute("issueBookBean", new IssueBookBean());
            return "issuebookform";
        }
        else {
            return "index";
        }
    }

    @PostMapping("/IssueBook")
    public ModelAndView postIssueBook(@ModelAttribute IssueBookBean issueBookBean, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            ModelAndView viewBook = new ModelAndView("listIssuedBook");
            issueService.insert(issueBookBean);
            List<IssueBookBean> list = issueService.getAll();
            viewBook.addObject("list", list);
            return viewBook;
        }
        else {
            ModelAndView viewBook = new ModelAndView("index");
            return viewBook;
        }
    }

    @GetMapping("/ReturnBook")
    public ModelAndView postReturnBook(@RequestParam("callno") String callno, @RequestParam("studentid") String studentid, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            ModelAndView viewBook = new ModelAndView("listIssuedBook");
            IssueBookBean issueBookBean = new IssueBookBean();
            System.out.println(callno);
            System.out.println(studentid);
            issueBookBean.setCallno(callno);
            issueBookBean.setStudentid(Integer.parseInt(studentid));
            issueService.returnBook(issueBookBean);
            List<IssueBookBean> list = issueService.getAll();
            viewBook.addObject("list", list);
            return viewBook;
        }
        else {
            ModelAndView viewBook = new ModelAndView("index");
            return viewBook;
        }
    }

    @GetMapping("/DeleteBook")
    public ModelAndView postDeleteBook(Model model, @RequestParam("callno") String callno, HttpServletRequest request) {
        if (checkLibrarianAuth(request)) {
            ModelAndView viewBook = new ModelAndView("listBook");
            BookBean bookBean = bookBeanService.getById(callno);
            bookBeanService.delete(bookBean);
            List<BookBean> list = bookBeanService.getAll();
            viewBook.addObject("list", list);
            return viewBook;
        }
        else {
            ModelAndView viewBook = new ModelAndView("index");
            return viewBook;
        }
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    private boolean checkLibrarianAuth( HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("userLogin") != "" && session.getAttribute("userLogin") != "admin";
    }
}
