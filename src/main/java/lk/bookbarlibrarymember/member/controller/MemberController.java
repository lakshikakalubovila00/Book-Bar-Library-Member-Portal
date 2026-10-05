package lk.bookbarlibrarymember.member.controller;

import lk.bookbarlibrarymember.member.dao.MemberDao;
import lk.bookbarlibrarymember.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class MemberController{

	@Autowired
	private MemberDao memberDao;

    // get member by member no
    @GetMapping(value = "/member/bymemberno/{memberno}", produces = "application/json")
    public Member getMemberByMemberno(@PathVariable String memberno) {
        return memberDao.filterByMemberno(memberno);
    }

    @GetMapping(value = "/member/loggedmember", produces = "application/json")
    public Member getLoggedMember(Authentication authentication) {
        String username = authentication.getName();
        return memberDao.getByUsername(username);
    }

}
