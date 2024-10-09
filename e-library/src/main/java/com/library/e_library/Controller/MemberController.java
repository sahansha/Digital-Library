package com.library.e_library.Controller;

import com.library.e_library.Model.Book;
import com.library.e_library.Model.Member;
import com.library.e_library.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(path = {"/add"},consumes = "application/json")
    public ResponseEntity<Member> addMember(@RequestBody Member member){

            Member savedMember=memberService.addMember(member);
            if(savedMember!=null)
            {
                return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping(value = {"/get"},produces = "application/json")
    public ResponseEntity<List<Member>> getAllMembers()
    {
        try{
            List<Member> members=this.memberService.getAllMembers();
            if(members.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(members,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = {"/{id}"},produces = "application/json")
    public ResponseEntity<Member> getMember(@PathVariable UUID id)
    {
        Member members=this.memberService.getMemberById(id);
        if(members!=null)
        {
            return new ResponseEntity<>(members,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = {"/update/{id}"},consumes = "application/json")
    public ResponseEntity<Member> updateMember(@PathVariable UUID id,@RequestBody Member member){
        return this.memberService.updateMember(id,member);
    }

    @GetMapping(value = {"/get-by-email"},produces = "application/json")
    public ResponseEntity<Member> getMemberByEmail(@RequestParam String email)
    {
        Member members=this.memberService.getMemberByEmail(email);
        if(members!=null)
        {
            return new ResponseEntity<>(members,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
