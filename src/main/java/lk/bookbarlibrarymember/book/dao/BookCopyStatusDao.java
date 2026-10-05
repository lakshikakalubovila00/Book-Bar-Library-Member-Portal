package lk.bookbarlibrarymember.book.dao;

import lk.bookbarlibrarymember.book.entity.BookCopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyStatusDao extends JpaRepository<BookCopyStatus, Integer> {

}
