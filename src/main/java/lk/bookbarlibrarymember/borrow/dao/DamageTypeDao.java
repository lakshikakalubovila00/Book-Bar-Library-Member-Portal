package lk.bookbarlibrarymember.borrow.dao;

import lk.bookbarlibrarymember.borrow.entity.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DamageTypeDao extends JpaRepository< DamageType, Integer> {
}
