package lk.bookbarlibrarymember.membershiptype.dao;

import jakarta.validation.constraints.NotNull;
import lk.bookbarlibrarymember.membershiptype.entity.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembershipTypeDao extends JpaRepository<MembershipType,Integer> {

    @Query(value="select mt from MembershipType mt where mt.membershiptypestatus_id.id=1")
    List<MembershipType> findValidMemberships();

    @Query(value="select mt from MembershipType mt where mt.name like '%Adult%' and mt.membershiptypestatus_id.id=1")
    List<MembershipType> findAdultValidMemberships();

    @Query(value="select mt from MembershipType mt where mt.name like '%Child%' and mt.membershiptypestatus_id.id=1")
    List<MembershipType> findChildValidMemberships();
}
