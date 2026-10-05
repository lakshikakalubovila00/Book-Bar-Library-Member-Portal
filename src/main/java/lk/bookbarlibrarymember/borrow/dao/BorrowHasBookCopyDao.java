package lk.bookbarlibrarymember.borrow.dao;

import lk.bookbarlibrarymember.borrow.entity.BorrowHasBookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowHasBookCopyDao extends JpaRepository<BorrowHasBookCopy,Integer> {

    @Query( value = "SELECT bhbc FROM BorrowHasBookCopy bhbc  WHERE bhbc.borrow_id.member_id.id= ?1 AND (bhbc.borrowhasbookcopystatus_id.id in(1,2,4))")
    List<BorrowHasBookCopy> getBorrowedBookCopiesByMember(Integer memberId);


    @Query( value = "SELECT bhbc FROM BorrowHasBookCopy bhbc  WHERE bhbc.borrow_id.borrowcode = ?1 ")
    List<BorrowHasBookCopy> getBorrowedBookCopiesByBorrowcode(String borrowcode);


    @Query( value = "SELECT bhbc FROM BorrowHasBookCopy bhbc  WHERE bhbc.borrow_id.member_id.id=?1 AND bhbc.borrowhasbookcopystatus_id.id=1 ")
    List<BorrowHasBookCopy> getBorrowedOnlyBookCopiesByMember(Integer memberid);

    @Query(value = "select bhbc from BorrowHasBookCopy bhbc where bhbc.bookcopy_id.id=?1 and  bhbc.borrowhasbookcopystatus_id.id in(1,2)")
    BorrowHasBookCopy getBorrowHasBookCopyByBookCopyId(Integer bookcopyid);

    @Query( value = "SELECT bhbc FROM BorrowHasBookCopy bhbc where bhbc.borrow_id.member_id.id=?1 and bhbc.borrowhasbookcopystatus_id.id=2")
    List<BorrowHasBookCopy> getRenewedBooksByMember(Integer memberid);

    @Query( value = "SELECT bhbc FROM BorrowHasBookCopy bhbc where bhbc.borrow_id.member_id.id=?1")
    List<BorrowHasBookCopy> geBorrowingsByMember(Integer memberid);
}
