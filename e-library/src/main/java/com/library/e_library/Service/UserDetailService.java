package com.library.e_library.Service;

import com.library.e_library.Model.Member;
import com.library.e_library.Model.UserPrincipal;
import com.library.e_library.Repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
   private final MemberRepository memberRepository;

    public UserDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member=this.memberRepository.findByUsername(username).orElse(null);
        if(member==null)
        {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(member);
    }
}
