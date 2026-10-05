package lk.bookbarlibrarymember.membershiptype.contoller;

import lk.bookbarlibrarymember.membershiptype.dao.MembershipTypeDao;
import lk.bookbarlibrarymember.membershiptype.entity.MembershipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembershipTypeContoller {

    @Autowired
    private MembershipTypeDao membershipTypeDao;


    @RequestMapping(value = "/membershiptype/valid" , produces = "application/json")
    public List<MembershipType> findValidMemberships() {
        return membershipTypeDao.findValidMemberships();
    }

    @RequestMapping(value = "/adultmembershiptypes/valid" , produces = "application/json")
    public List<MembershipType> findAdultValidMemberships() {
        return membershipTypeDao.findAdultValidMemberships();
    }

    @RequestMapping(value = "/childmembershiptypes/valid" , produces = "application/json")
    public List<MembershipType> findChildValidMemberships() {
        return membershipTypeDao.findChildValidMemberships();
    }

}
