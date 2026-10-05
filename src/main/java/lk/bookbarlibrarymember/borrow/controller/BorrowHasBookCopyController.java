package lk.bookbarlibrarymember.borrow.controller;

import lk.bookbarlibrarymember.borrow.dao.BorrowHasBookCopyDao;
import lk.bookbarlibrarymember.borrow.entity.BorrowHasBookCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorrowHasBookCopyController {
    @Autowired
    private BorrowHasBookCopyDao borrowHasBookCopyDao;


    @GetMapping(value = "/borrowhasbookcopy/byid/{borrowhasbookcopyid}", produces = "application/json")
    public BorrowHasBookCopy getBorrowHasBookCopyById(@PathVariable Integer borrowhasbookcopyid){
        return borrowHasBookCopyDao.getReferenceById(borrowhasbookcopyid);
    }
    @GetMapping(value = "/borrowhasbookcopy/bybookcopyid/{bookcopyid}", produces = "application/json")
    public BorrowHasBookCopy getBorrowHasBookCopyByBookCopyId(@PathVariable Integer bookcopyid){
        return borrowHasBookCopyDao.getBorrowHasBookCopyByBookCopyId(bookcopyid);
    }

    @GetMapping(value = "/renwedbooks/bymember/{memberid}", produces = "application/json")
    public List<BorrowHasBookCopy> getRenewedBooksByMember(@PathVariable Integer memberid){
        return borrowHasBookCopyDao.getRenewedBooksByMember(memberid);
    }
    @GetMapping(value = "/borrowings/bymember/{memberid}", produces = "application/json")
    public List<BorrowHasBookCopy> geBorrowingsByMember(@PathVariable Integer memberid){
        return borrowHasBookCopyDao.geBorrowingsByMember(memberid);
    }

}
