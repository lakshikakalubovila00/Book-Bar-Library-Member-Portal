package lk.bookbarlibrarymember.book.dao;

import lk.bookbarlibrarymember.book.entity.BookCopy;
import lk.bookbarlibrarymember.book.entity.DisplayCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyDao extends JpaRepository<BookCopy,Integer> {

    @Query(value = "select bc from BookCopy bc where bc.accessionno=?1")
    BookCopy getBookCopyByAccesionNo(String accessionno);

    @Query(value = "select bc from BookCopy bc where bc.accessionno=?1 and bc.bookcopystatus_id.id in(1,2) and bc.damagestatus_id.id not in (3,4)")
    BookCopy getBookCopyForReservationByAccesionNo(String accessionno);

    @Query(value = "select bc from BookCopy bc where bc.book_id.title=?1 and bc.bookcopystatus_id.id not in (4)")
    List<BookCopy> getBookCopiesByTitle(String title);

    @Query(value = "select bc from BookCopy bc where bc.book_id.author=?1 and bc.bookcopystatus_id.id not in (4)")
    List<BookCopy> getAllBookCopiessByAuthor(String author);

    @Query(value = "select bc from BookCopy bc where bc.book_id.isbn=?1 and bc.bookcopystatus_id.id not in (4)")
    List<BookCopy> getAllBookCopiessByISBN(String isbn);

    @Query(value = "select bc from BookCopy bc where bc.book_id.issn=?1 and bc.bookcopystatus_id.id not in (4)")
    List<BookCopy> getAllBookCopiessByISSN(String issn);

    @Query(value = "select bc from BookCopy bc where bc.book_id.seriestitle=?1 and bc.bookcopystatus_id.id not in (4)")
    List<BookCopy> getAllBookCopiessBySeries(String series);

    @Query(value = "select bc from BookCopy bc where bc.book_id.displaycategory_id.name=?1 and bc.bookcopystatus_id.id not in (4)")
    List<BookCopy> getAllBookCopiessByDisplayCategory(String displaycategory);
}
