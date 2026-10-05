package lk.bookbarlibrarymember.membership.controller;

import lk.bookbarlibrarymember.membership.dao.MembershipDao;
import lk.bookbarlibrarymember.membership.entity.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MembershipController {
    @Autowired
    private MembershipDao membershipDao;

    //get last record of a member's memberships
    @GetMapping(value = "/membership/bymember/{memberid}", produces = "application/json")
    public Membership getMembershipByMemberid(@PathVariable Integer memberid) {
        return membershipDao.filterbymemberid(memberid);
    }

}
