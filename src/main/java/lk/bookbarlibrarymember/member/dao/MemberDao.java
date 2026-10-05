package lk.bookbarlibrarymember.member.dao;

import lk.bookbarlibrarymember.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberDao extends JpaRepository<Member, Integer> {

    @Query(value = "select m from Member m where m.username=?1")
    Member getByUsername(String username);

    @Query(value="select m from Member m where m.memberno=?1")
    Member filterByMemberno(String memberno);

    @Query(value = "select m from Member m where m.email=?1")
    Member getByEmail(String email);
}
