package com.library.e_library.service;

import com.library.e_library.ELibraryApplication;
import com.library.e_library.Model.Book;
import com.library.e_library.Model.Member;
import com.library.e_library.Repository.MemberRepository;
import com.library.e_library.Service.MemberService;
import com.library.e_library.enums.Category;
import com.library.e_library.enums.SubscriptionStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ELibraryApplication.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member=Member.builder().id(UUID.randomUUID())
            .email("xyz@gmail.com").firstName("Sahansha")
            .lastName("Khan").mobileNumber("12344555").subscriptionStatus(SubscriptionStatus.ACTIVE)
            .build();
    @Test
    public void addmemberTest()
    {
        when(memberRepository.save(member)).thenReturn(member);
        assertEquals(member,memberService.addMember(member));
    }
}
