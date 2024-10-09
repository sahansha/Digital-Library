package com.library.e_library.Service;

import com.library.e_library.Model.Member;
import com.library.e_library.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member)
    {
        try{
            return this.memberRepository.save(member);
        }
        catch (Exception e)
        {
            log.info("Following Exception Occurred While adding Member : {}",e.getMessage());
            return null;
        }
    }
    public List<Member> getAllMembers()
    {
            return this.memberRepository.findAll();
    }
    public Member getMemberById(UUID id)
    {
        return this.memberRepository.findById(id).orElse(null);
    }

    public Member getMemberByEmail(String email)
    {
        return this.memberRepository.findByEmail(email).orElse(null);
    }

    public ResponseEntity<Member> updateMember(UUID id, Member member)
    {
        try{
            Optional<Member> foundMember=this.memberRepository.findById(id);
            if(foundMember.isPresent())
            {
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
                Member updatedMember= this.memberRepository.save(_member);
                return new ResponseEntity<>(updatedMember, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
