package lk.bookbarlibrarymember.holidays.controller;

import lk.bookbarlibrarymember.holidays.dao.HolidaysDao;
import lk.bookbarlibrarymember.holidays.entity.Holidays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HolidaysContoller {

    @Autowired
    private HolidaysDao holidaysDao;


    @GetMapping(value = "/holidays/alldata")
    public List<Holidays> getHolidaysList(){
            return holidaysDao.findAll();

    }

    @RequestMapping(value="/calendarview")
    public ModelAndView getUi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView calendarView = new ModelAndView();
        calendarView.addObject("loggedusername", authentication.getName());
        calendarView.addObject("title", "Calendar");
        calendarView.setViewName("calendar.html");
        return calendarView;
    }

}
