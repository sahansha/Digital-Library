package com.library.e_library.service;

import com.library.e_library.ELibraryApplication;
import com.library.e_library.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ELibraryApplication.class)
public class MemberServiceTest {

    private final MemberRepository memberRepository;

    public MemberServiceTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

   /* @Test
    public void addMember_*/
}
