package lk.bookbarlibrarymember.borrow.dao;

import lk.bookbarlibrarymember.borrow.entity.BorrowHasBookCopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowHasBookCopyStatusDao extends JpaRepository<BorrowHasBookCopyStatus, Integer> {
}
