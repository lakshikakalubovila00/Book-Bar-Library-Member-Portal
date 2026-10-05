package lk.bookbarlibrarymember.borrow.dao;

import lk.bookbarlibrarymember.borrow.entity.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowStatusDao extends JpaRepository<BorrowStatus, Integer> {
}
