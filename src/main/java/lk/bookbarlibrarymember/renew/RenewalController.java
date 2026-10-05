package lk.bookbarlibrarymember.renew;

import lk.bookbarlibrarymember.book.dao.BookCopyDao;
import lk.bookbarlibrarymember.book.dao.BookCopyStatusDao;
import lk.bookbarlibrarymember.book.entity.BookCopy;
import lk.bookbarlibrarymember.borrow.dao.BorrowDao;
import lk.bookbarlibrarymember.borrow.dao.BorrowHasBookCopyDao;
import lk.bookbarlibrarymember.borrow.dao.BorrowHasBookCopyStatusDao;
import lk.bookbarlibrarymember.borrow.dao.BorrowStatusDao;
import lk.bookbarlibrarymember.borrow.entity.Borrow;
import lk.bookbarlibrarymember.borrow.entity.BorrowHasBookCopy;
import lk.bookbarlibrarymember.reservation.dao.ReservationDao;
import lk.bookbarlibrarymember.reservation.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RenewalController {
    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private BorrowHasBookCopyStatusDao borrowHasBookCopyStatusDao;

    @Autowired
    private BorrowHasBookCopyDao borrowHasBookCopyDao;

    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private BookCopyStatusDao bookCopyStatusDao;

    @Autowired
    private BorrowStatusDao borrowStatusDao;

    @Autowired
    private ReservationDao reservationDao;

    @RequestMapping(value="/renewal")
    public ModelAndView renewalUI(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView renewalView = new ModelAndView();
        renewalView.addObject("loggedusername", authentication.getName());
        renewalView.addObject("title", "Book Renewal Management");
        renewalView.setViewName("renewal.html");
        return renewalView;
    }

    @PutMapping(value = "/renewal/saverenewbooks")
    public String handoverBooksSave(@RequestBody List<Borrow> borrowList) {
        //checked loged user has permission for insert borrow record (check user authentication and authorization)
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Privilege userPrivi = authController.getPrivilegeByUserAndModule(authentication.getName(),"Book Renewals");
//        if(!userPrivi.getPrivi_insert()){
//            return "Book Renewal Process not completed : User haven't permission.";
//        }
        try{
            //set auto generated values
            for(Borrow borrowReq : borrowList) {
                // get borrow by code
                Borrow borrow = borrowDao.getReferenceById(borrowReq.getId());

                if(borrowReq.getBorrowHasBookCopiesList() !=null){
                    for (BorrowHasBookCopy bhbcReq : borrowReq.getBorrowHasBookCopiesList()) {

                        BorrowHasBookCopy extBhbc = borrowHasBookCopyDao.findById(bhbcReq.getId()).orElse(null);
                        if(extBhbc == null){
                            continue;
                        }

                        // maintain relationship
                        extBhbc.setBorrow_id(borrow);

                        // set handover data
                        extBhbc.setRenewdate(bhbcReq.getRenewdate());
                        extBhbc.setRenewhandoverduedate(bhbcReq.getRenewhandoverduedate());

                        // change borrow has book copy status -> renewed
                        extBhbc.setBorrowhasbookcopystatus_id(borrowHasBookCopyStatusDao.getReferenceById(2));
                        System.out.println(extBhbc);

                        borrowHasBookCopyDao.save(extBhbc);

                        // if this book copy is reserved need to extend the reservation borrow date into the renew handover due date
                        BookCopy bookCopy = extBhbc.getBookcopy_id();

                        if(bookCopy.getIsreserved()){
                           LocalDate extendedDate= extBhbc.getRenewhandoverduedate();
                            // get reservation
                            Reservation reservation = reservationDao.getActiveReservationByBookCopy(bookCopy.getId());

                            if (reservation != null) {
                                // extend reservation borrow date (or due date based on your design)
                                reservation.setBorrowdate(extendedDate);

                                reservationDao.save(reservation);
                            }
                        }

                        // set book copy status to borrowed
                        bookCopy.setBookcopystatus_id(bookCopyStatusDao.getReferenceById(2)); // 2 = borrowed

                        bookCopyDao.save(bookCopy);
                    }
                }
                List<BorrowHasBookCopy> allBooks = borrowHasBookCopyDao.getBorrowedBookCopiesByBorrowcode(borrow.getBorrowcode());
                int renewedCount = 0;

                for(BorrowHasBookCopy bhbc : allBooks){
                    if(bhbc.getBorrowhasbookcopystatus_id().getId() == 2){
                        renewedCount++;
                    }
                }
                if(renewedCount == 0){
                    borrow.setBorrowstatus_id(
                            borrowStatusDao.getReferenceById(1)); // Borrowed

                }else if(renewedCount < allBooks.size()){

                    borrow.setBorrowstatus_id(
                            borrowStatusDao.getReferenceById(4)); // Partially Renewed

                }else if(renewedCount == allBooks.size()){

                    borrow.setBorrowstatus_id(
                            borrowStatusDao.getReferenceById(5)); // Fully Renewed
                }

                borrow.setUpdateddatetime(LocalDateTime.now());
                // because updated user equals to member in member portal
                borrow.setUpdateduserid(null);

                borrowDao.save(borrow);
            }

            return "OK";
        }catch(Exception e){
            return "Book Renewal Process not completed."+e.getMessage();
        }
    }

}
