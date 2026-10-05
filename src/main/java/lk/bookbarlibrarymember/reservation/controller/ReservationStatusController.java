package lk.bookbarlibrarymember.reservation.controller;

import lk.bookbarlibrarymember.reservation.dao.ReservationStatusDao;
import lk.bookbarlibrarymember.reservation.entity.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReservationStatusController {
    @Autowired
    private ReservationStatusDao reservationStatusDao;

    @GetMapping(value = "/reservationstatus/alldata", produces = "application/json")
    public List<ReservationStatus> getAllReservationStatus() {
        return reservationStatusDao.findAll();
    }
    @GetMapping(value = "/reservationstatus/formember", produces = "application/json")
    public List<ReservationStatus> getReservationStatusForMember() {
        return reservationStatusDao.getReservationStatusForMember();
    }
}
