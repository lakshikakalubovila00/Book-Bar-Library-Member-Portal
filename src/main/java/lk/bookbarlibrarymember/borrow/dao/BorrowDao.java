package lk.bookbarlibrarymember.borrow.dao;

import lk.bookbarlibrarymember.borrow.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowDao extends JpaRepository<Borrow,Integer> {

    @Query(value = "SELECT COUNT(bhbc.id) FROM BorrowHasBookCopy bhbc WHERE bhbc.borrow_id.member_id.id=?1 AND bhbc.borrowhasbookcopystatus_id.id in (1,2,4)")
    Integer getBooksOnHandByMember(Integer memberid);



}
