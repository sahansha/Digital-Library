package com.library.e_library.Service;

import com.library.e_library.Model.Member;
import com.library.e_library.Repository.MemberRepository;
import com.library.e_library.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public Member addMember(Member member)
    {
        try{
            member.setPassword(this.bCryptPasswordEncoder.encode(member.getPassword()));
            return this.memberRepository.save(member);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public List<Member> getAllMembers()
    {
            return this.memberRepository.findAll();
    }
    public Member getMemberById(UUID id)
    {
        try{
            Member member= this.memberRepository.findById(id).orElse(null);
            if(member==null)
            {
                throw new UserNotFoundException("Member does not Exist");
            }
            return member;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public Member getMemberByEmail(String email)
    {
        try{
            Member member= this.memberRepository.findByEmail(email).orElse(null);
            if(member==null)
            {
                throw new UserNotFoundException("Member does not Exist");
            }
            return member;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Member updateMember(UUID id, Member member)
    {
        try{
            Optional<Member> foundMember=this.memberRepository.findById(id);
            if(foundMember.isEmpty())
            {
                throw new UserNotFoundException("Member record not available to update");
            }
                Member _member=foundMember.get();
                if(member.getEmail()!=null)
                {
                    _member.setEmail(member.getEmail());
                }
                if(member.getMobileNumber()!=null)
                {
                    _member.setMobileNumber(member.getMobileNumber());
                }
                if(member.getFirstName()!=null)
                {
                    _member.setFirstName(member.getFirstName());
                }
                if(member.getLastName()!=null)
                {
                    _member.setLastName(member.getLastName());
                }
                if(member.getSubscriptionStatus()!=null)
                {
                    _member.setSubscriptionStatus(member.getSubscriptionStatus());
                }
            return this.memberRepository.save(_member);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
