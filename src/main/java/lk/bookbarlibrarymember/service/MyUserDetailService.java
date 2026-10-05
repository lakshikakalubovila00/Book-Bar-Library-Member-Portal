package lk.bookbarlibrarymember.service;


import jakarta.transaction.Transactional;
import lk.bookbarlibrarymember.member.dao.MemberDao;
import lk.bookbarlibrarymember.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private MemberDao memberDao;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member loggedMember= memberDao.getByUsername(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Member"));

        return new org.springframework.security.core.userdetails.User(loggedMember.getUsername(),loggedMember.getPassword(),
                loggedMember.getAccountstatus(),true,true,true,authorities);
    }
}
