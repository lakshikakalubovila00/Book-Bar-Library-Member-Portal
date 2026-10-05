package lk.bookbarlibrarymember.mydetails.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyDetailsController {

    @GetMapping(value="/myborrowings")
    public ModelAndView myBorrowingsView(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView myBorrowingsView = new ModelAndView();
        myBorrowingsView.addObject("loggedusername" , authentication.getName());
        myBorrowingsView.addObject("title", "My Borrowings");
        myBorrowingsView.setViewName("myborrowings.html");
        return myBorrowingsView;
    }

    @GetMapping(value="/myreservations")
    public ModelAndView myReservationsView(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView myReservationsView = new ModelAndView();
        myReservationsView.addObject("loggedusername" , authentication.getName());
        myReservationsView.addObject("title", "My Reservations");
        myReservationsView.setViewName("myreservations.html");
        return myReservationsView;
    }
}
