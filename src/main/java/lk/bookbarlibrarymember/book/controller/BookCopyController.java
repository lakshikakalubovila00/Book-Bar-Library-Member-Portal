package lk.bookbarlibrarymember.book.controller;


import lk.bookbarlibrarymember.CommonController;
import lk.bookbarlibrarymember.book.dao.BookCopyDao;
import lk.bookbarlibrarymember.book.dao.BookDao;
import lk.bookbarlibrarymember.book.entity.Book;
import lk.bookbarlibrarymember.book.entity.BookCopy;
import lk.bookbarlibrarymember.book.entity.DisplayCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookCopyController  {

    @Autowired
    private BookCopyDao bookCopyDao;


    @GetMapping(value = "/bookcopy/byid/{bookcopyid}", produces = "application/json")
    public BookCopy getBookCopyById(@PathVariable("bookcopyid") Integer bookcopyid) {
        return bookCopyDao.getReferenceById(bookcopyid);
    }

    @GetMapping(value = "/bookcopy/byaccessionno/{accessionno}" ,produces = "application/json")
    public BookCopy getBookCopyByAccesionNo(@PathVariable String accessionno){
        return bookCopyDao.getBookCopyByAccesionNo(accessionno);
    }
    @GetMapping(value = "/bookcopyforreservation/byaccessionno/{accessionno}" ,produces = "application/json")
    public BookCopy getAvailableBookCopyForReservationByAccesionNo(@PathVariable String accessionno){
        return bookCopyDao.getBookCopyForReservationByAccesionNo(accessionno);
    }

    @GetMapping(value = "/bookcopies/bytitle/{title}", produces = "application/json")
    public List<BookCopy> findAllBooksByTitle(@PathVariable String title){
        return bookCopyDao.getBookCopiesByTitle(title);
    }
    @GetMapping(value = "/bookcopies/byauthor/{author}", produces = "application/json")
    public List<BookCopy> findAllBooksByAuthor(@PathVariable String author){
        return bookCopyDao.getAllBookCopiessByAuthor(author);
    }
    @GetMapping(value = "/bookcopies/byisbn/{isbn}", produces = "application/json")
    public List<BookCopy> findAllBooksByISBN(@PathVariable String isbn){
        return bookCopyDao.getAllBookCopiessByISBN(isbn);
    }
    @GetMapping(value = "/bookcopies/byissn/{issn}", produces = "application/json")
    public List<BookCopy> findAllBooksByISSN(@PathVariable String issn){
        return bookCopyDao.getAllBookCopiessByISSN(issn);
    }
    @GetMapping(value = "/bookcopies/byseries/{series}", produces = "application/json")
    public List<BookCopy> findAllBooksBySeries(@PathVariable String series){
        return bookCopyDao.getAllBookCopiessBySeries(series);
    }
    @GetMapping(value = "/bookcopies/bydisplaycategory/{displaycategory}", produces = "application/json")
    public List<BookCopy> findAllBooksByDisplayCategory(@PathVariable String displaycategory){
        return bookCopyDao.getAllBookCopiessByDisplayCategory(displaycategory);
    }

}
