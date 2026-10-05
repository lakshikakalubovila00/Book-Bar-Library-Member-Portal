package lk.bookbarlibrarymember.book.controller;


import lk.bookbarlibrarymember.book.dao.BookDao;
import lk.bookbarlibrarymember.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookDao bookDao;

    @GetMapping(value = "/book/titles", produces = "application/json")
    public List<String> findAllTitles(){
        return bookDao.findAllTitles();
    }

    @GetMapping(value = "/book/authors", produces = "application/json")
    public List<String> findAllAuthors(){
        return bookDao.findAllAuthors();
    }

    @GetMapping(value = "/book/isbns", produces = "application/json")
    public List<String> findAllISBSNs(){
        return bookDao.findAllISBSNs();
    }

    @GetMapping(value = "/book/issns", produces = "application/json")
    public List<String> findAllISSNs(){
        return bookDao.findAllISSNs();
    }

    @GetMapping(value = "/book/publishers", produces = "application/json")
    public List<String> findAllPublishers(){
        return bookDao.findAllPublishers();
    }

    @GetMapping(value = "/book/series", produces = "application/json")
    public List<String> findAllSeries(){
        return bookDao.findAllSeries();
    }

    @GetMapping(value = "/book/displaycategories", produces = "application/json")
    public List<String> findAllDisplayCategories(){
        return bookDao.findAllDisplayCategories();
    }

	
}
