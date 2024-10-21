package com.library.e_library.Controller;

import com.library.e_library.Model.Book;
import com.library.e_library.Model.Member;
import com.library.e_library.Service.JwtService;
import com.library.e_library.Service.MemberService;
import com.library.e_library.dto.MemberAuthDto;
import com.library.e_library.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    @Autowired
    public MemberController(MemberService memberService
            ,AuthenticationManager authenticationManager,JwtService jwtService) {
        this.memberService = memberService;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

    @PostMapping(path = {"/add"},consumes = "application/json")
    public ResponseEntity<EntityModel<Member>> addMember(@Valid @RequestBody Member member){

            Member savedMember=memberService.addMember(member);
            EntityModel<Member> entityModel=EntityModel.of(savedMember);
            WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                    .getMember(savedMember.getId()));
            WebMvcLinkBuilder link1=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                    .getAllMembers());
            entityModel.add(link.withRel("user-id"));
            entityModel.add(link1.withRel("all-user"));

    /*    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();*/


            return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    @GetMapping(value = {"/get"},produces = "application/json")
    public ResponseEntity<List<Member>> getAllMembers()
    {
            List<Member> members=this.memberService.getAllMembers();
            return new ResponseEntity<>(members,HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"},produces = "application/json")
    public ResponseEntity<Member> getMember(@PathVariable UUID id)
    {
        Member member=this.memberService.getMemberById(id);
        return new ResponseEntity<>(member,HttpStatus.OK);

    }

    @PatchMapping(path = {"/update/{id}"},consumes = "application/json")
    public ResponseEntity<Member> updateMember(@PathVariable UUID id,@RequestBody Member member){
        Member member1= this.memberService.updateMember(id,member);
        return new ResponseEntity<>(member1,HttpStatus.OK);
    }

    @GetMapping(value = {"/get-by-email"},produces = "application/json")
    public ResponseEntity<EntityModel<Member>> getMemberByEmail(@RequestParam String email)
    {
        Member members=this.memberService.getMemberByEmail(email);
        EntityModel<Member> entityModel=EntityModel.of(members);
        WebMvcLinkBuilder webMvcLinkBuilder=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                            .methodOn(this.getClass()).getMember(members.getId()));
        entityModel.add(webMvcLinkBuilder.withRel("member-by-id"));
        return new ResponseEntity<>(entityModel,HttpStatus.OK);

    }

    @PostMapping("/login")
    public String authLogin(@RequestBody MemberAuthDto memberAuthDto)
    {
        Authentication authentication=this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(memberAuthDto.getUsername(),memberAuthDto.getPassword())        );
        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(memberAuthDto.getUsername());
        }
        return "Not Authorized";
    }

}
