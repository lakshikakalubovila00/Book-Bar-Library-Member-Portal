package lk.bookbarlibrarymember.reservation.controller;

import lk.bookbarlibrarymember.CommonController;
import lk.bookbarlibrarymember.book.dao.BookCopyDao;
import lk.bookbarlibrarymember.book.entity.BookCopy;
import lk.bookbarlibrarymember.member.dao.MemberDao;
import lk.bookbarlibrarymember.member.entity.Member;
import lk.bookbarlibrarymember.notification.dao.NotificationUserDao;
import lk.bookbarlibrarymember.notification.entity.NotificationUser;
import lk.bookbarlibrarymember.reservation.dao.ReservationDao;
import lk.bookbarlibrarymember.reservation.dao.ReservationStatusDao;
import lk.bookbarlibrarymember.reservation.entity.Reservation;
import lk.bookbarlibrarymember.user.dao.UserDao;
import lk.bookbarlibrarymember.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReservationController  {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationStatusDao reservationStatusDao;

    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NotificationUserDao notificationUserDao;

    @RequestMapping(value="/reservation")
    public ModelAndView getUi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView bookreservationView = new ModelAndView();
        bookreservationView.addObject("loggedusername", authentication.getName());
        bookreservationView.addObject("title", "Book Reservation Management");
        bookreservationView.setViewName("bookreservation.html");
        return bookreservationView;
    }


    @GetMapping(value = "/reservation/byid/{reservationid}" ,produces = "application/json")
    public Reservation getReservationById(@PathVariable Integer reservationid) {
        return reservationDao.getReferenceById(reservationid);
    }

    @GetMapping(value = "/reservation/reservedcount/{memberid}")
    public Integer getReservedBookCount(@PathVariable Integer memberid){
        return reservationDao.getReservedBookCountByMember(memberid);
    }

    @GetMapping(value = "/reservation/bymember/{memberid}", produces = "application/json")
    public Reservation getReservationByMemberId(@PathVariable Integer memberid){
        return reservationDao.getReservationByMember(memberid);
    }

    @GetMapping(value = "/reservations/bymember/{memberid}", produces = "application/json")
    public List<Reservation> getReservationsByMemberId(@PathVariable Integer memberid){
        return reservationDao.getReservationsByMember(memberid);
    }


    @GetMapping(value = "/reservation/bybookcopyid/{bookcopyid}", produces = "application/json")
    public List<Reservation> getReservationByBookCopyId(@PathVariable Integer bookcopyid) {
        return reservationDao.getReservationByBookCopyId(bookcopyid);
    }

    @GetMapping(value = "/reservationcount/bybookcopyid/{bookcopyid}", produces = "application/json")
    public Integer getReservationCountByBookCopy(@PathVariable Integer bookcopyid){
        return reservationDao.getReservationCountByBookCopy(bookcopyid);
    }

    @PostMapping(value = "/reservation/insert")
    public String saveData(@RequestBody Reservation reservation) {
        try{

            // if reservation status is pending make notification
            if(reservation.getReservationstatus_id().getId()==1) {
                Member member = reservation.getMember_id();
                BookCopy bookCopy = reservation.getBookcopy_id();

                // create notification
                NotificationUser notificationuser = new NotificationUser();
                notificationuser.setTitle("New reservation received");
                notificationuser.setMessagetext("Reservation added for " +
                        bookCopy.getAccessionno() + " - " + bookCopy.getBook_id().getTitle() +
                        " .");
                User librarian = userDao.findByDesignation("LIBRARIAN");

                notificationuser.setUser_id(librarian);
                notificationuser.setIsread(false);
                notificationuser.setAddeddatetime(LocalDate.now());
                notificationUserDao.save(notificationuser);
            }

            // set auto generated value
            // set added date time
            reservation.setAddeddatetime(LocalDateTime.now());
            // set added user id
            // because added user equals to member in member portal
            //reservation.setAddeduserid(null);

            reservation.setReservationno(reservationDao.getNextReservationNo());

            // set book copy as reserved
            BookCopy bookCopy= reservation.getBookcopy_id();

            bookCopy.setIsreserved(true);

            bookCopyDao.save(bookCopy);
            // operation
            reservationDao.save(reservation);

            // check dependencies
            return "OK";
        }catch (Exception e){
            return "Reservation insert not completed. "+e.getMessage();
        }
    }

    @PutMapping(value = "/reservation/update")
    public String updateData(@RequestBody Reservation reservation) {
        // check existence
        if(reservation.getId()==null){
            return "Reservation Update not completed : Reservation Not Exist..";
        }
        Reservation extReservation= reservationDao.getReferenceById(reservation.getId());
        if(extReservation.getId()==null){
            return "Reservation Update not completed : Reservation Not Exist..";
        }
        try{
            // set auto generated value
            // set added date time
            reservation.setUpdateddatetime(LocalDateTime.now());
            // set added user id to null
            // because updated user equals to member in member portal
            reservation.setUpdateduserid(null);

            // if reservation status is cancelled make book copy  - is reserved false
            if(reservation.getReservationstatus_id().getId()==4){
                BookCopy bookCopy= reservation.getBookcopy_id();
                if (bookCopy != null) {
                    bookCopy.setIsreserved(false);
                    bookCopyDao.save(bookCopy);
                }
            }
            // operation
            reservationDao.save(reservation);

            // check dependencies
            return "OK";
        }catch (Exception e){
            return "Reservation update not completed. "+e.getMessage();
        }

    }
}
