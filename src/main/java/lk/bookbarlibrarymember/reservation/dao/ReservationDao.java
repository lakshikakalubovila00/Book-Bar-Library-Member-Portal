package lk.bookbarlibrarymember.reservation.dao;

import lk.bookbarlibrarymember.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT COUNT(r.id) FROM Reservation r WHERE r.member_id.id=?1 AND r.bookcopy_id.isreserved=true")
    Integer getReservedBookCountByMember(Integer memberid);

    @Query(value = "SELECT coalesce(concat(year(current_date()),lpad(substring(max(r.reservationno),5)+1,8,'0')),concat(year(current_date()),'00000001')) FROM Reservation r where year(current_date())=year(r.reserveddate);", nativeQuery = true)
    String getNextReservationNo();

    @Query(value = "select r from Reservation r where r.bookcopy_id.id=?1 and r.reservationstatus_id.id in(1,2) and r.bookcopy_id.isreserved=true")
    List<Reservation> getReservationByBookCopyId(Integer bookcopyid);

    @Query("SELECT r FROM Reservation r WHERE r.bookcopy_id.id = ?1 AND r.reservationstatus_id.id in(1,2)")
    Reservation getActiveReservationByBookCopy(Integer bookCopyId);

    @Query(value = "select r from Reservation r where  r.member_id.id=?1 and r.bookcopy_id.isreserved=true")
    Reservation getReservationByMember(Integer memberid);

    @Query(value = "select r from Reservation r where  r.member_id.id=?1")
    List<Reservation> getReservationsByMember(Integer memberid);

    @Query(value = "select count(r.id) from Reservation r where r.bookcopy_id.id=?1 and r.reservationstatus_id.id in(1,2) and r.bookcopy_id.isreserved=true")
    Integer getReservationCountByBookCopy(Integer bookcopyid);
}
