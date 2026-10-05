package lk.bookbarlibrarymember.holidays.dao;

import lk.bookbarlibrarymember.holidays.entity.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidaysDao extends JpaRepository<Holidays, Integer> {
}
