package lk.bookbarlibrarymember.membership.dao;

import jakarta.validation.constraints.NotNull;
import lk.bookbarlibrarymember.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MembershipDao extends JpaRepository<Membership,Integer> {

    @Query(value = "select m from Membership m where m.id=(select max(m.id)from Membership m where m.member_id.id=?1)")
    Membership filterbymemberid(Integer memberid);
    //DESC--descending order (highest ID first)

}
