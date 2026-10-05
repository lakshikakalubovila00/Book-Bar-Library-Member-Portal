package lk.bookbarlibrarymember.borrow.controller;

import lk.bookbarlibrarymember.borrow.dao.BorrowDao;
import lk.bookbarlibrarymember.borrow.dao.BorrowHasBookCopyDao;
import lk.bookbarlibrarymember.borrow.entity.BorrowHasBookCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowController {

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private BorrowHasBookCopyDao borrowHasBookCopyDao;

    @GetMapping(value = "/borrow/booksonhand/{memberid}")
    public Integer getBooksOnHand(@PathVariable Integer memberid){
        return borrowDao.getBooksOnHandByMember(memberid);
    }

    @GetMapping(value = "/borrow/borrowedbooks/{memberid}", produces = "application/json")
    public List<BorrowHasBookCopy> getBorrowedBooks(@PathVariable Integer memberid){
        return borrowHasBookCopyDao.getBorrowedBookCopiesByMember(memberid);
    }
    @GetMapping(value = "/borrow/borrowedonlybooks/{memberid}", produces = "application/json")
    public List<BorrowHasBookCopy> getBorrowedOnlyBooks(@PathVariable Integer memberid){
        return borrowHasBookCopyDao.getBorrowedOnlyBookCopiesByMember(memberid);
    }



}
