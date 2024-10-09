package com.library.e_library.Service;

import com.library.e_library.Model.Book;
import com.library.e_library.Model.IssueData;
import com.library.e_library.Model.Member;
import com.library.e_library.Repository.IssueDataRepository;
import com.library.e_library.dto.IssueDataDto;
import com.library.e_library.enums.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class IssueDataService {
    private final IssueDataRepository issueDataRepository;
    private final MemberService memberService;
    private final BookService bookService;
    @Autowired
    public IssueDataService(IssueDataRepository issueDataRepository, MemberService memberService, BookService bookService) {
        this.issueDataRepository = issueDataRepository;
        this.memberService = memberService;
        this.bookService = bookService;
    }



    public IssueData addIssueData(IssueDataDto issueDataDto)
    {
        try {
            Member member=memberService.getMemberById(issueDataDto.getMemberId());
            Book book=bookService.getBookById(issueDataDto.getBookId());
            IssueData issueData= IssueData.builder()
                    .book(book)
                    .member(member)
                    .build();
            return addIssueData(issueData);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public IssueData addIssueData(IssueData issueData)
    {
        try{
            issueData.calculateExpirationdate();
            issueData.calculateSubscriptionAmount();
            return this.issueDataRepository.save(issueData);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }

    }

    public List<IssueData> getIssueDataById(UUID id)
    {
        try{
            return this.issueDataRepository.findByMemberId(id);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }

    }
    public String updateStatus(List<IssueData> issueData)
    {
        try {
            IssueData issueList=null;
            for(IssueData list:issueData)
            {
                issueList=IssueData.builder().build().withIssueStatus(IssueStatus.EXPIRED);
                this.issueDataRepository.save(issueList);
            }
            return "updated";
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String updateStatus(UUID memberId)
    {
        try{
            List<IssueData> issueList=this.issueDataRepository
                                        .findByMemberId(memberId)
                                        .stream()
                                        .filter(issue-> Instant.now().isAfter(issue.getExpirationDate()))
                                        .toList();
            return this.updateStatus(issueList);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

}
