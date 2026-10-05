package lk.bookbarlibrarymember.reservation.dao;

import lk.bookbarlibrarymember.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationStatusDao extends JpaRepository<ReservationStatus, Integer> {

    @Query(value = "select rs from ReservationStatus rs where rs.id in(1,4)")
    List<ReservationStatus> getReservationStatusForMember();
}
