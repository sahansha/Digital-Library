package com.library.e_library.Service;

import com.library.e_library.Model.Book;
import com.library.e_library.Model.IssueData;
import com.library.e_library.Model.Member;
import com.library.e_library.Repository.IssueDataRepository;
import com.library.e_library.dto.IssueDataDto;
import com.library.e_library.enums.IssueStatus;
import com.library.e_library.exception.IssueDataNotFound;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@EnableAspectJAutoProxy
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

    public List<IssueData> getIssueDataById(UUID id) {
        try{
            List<IssueData> issueDataList= this.issueDataRepository.findByMemberId(id);
            if(issueDataList.isEmpty())
            {
                throw new IssueDataNotFound("Issue Data not found");
            }
            return issueDataList;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
    public String updateStatus(List<IssueData> issueData)
    {
        try {
            for(IssueData list:issueData)
            {
                list.setIssueStatus(IssueStatus.EXPIRED);
                this.issueDataRepository.save(list);
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
            List<IssueData> issueList=this.issueDataRepository.findByMemberId(memberId).stream()
                    .filter(issue-> Instant.now().isAfter(issue.getExpirationDate()))
                    .toList();;
            if(issueList.isEmpty())
            {
                throw new IssueDataNotFound("Issue Data not found");
            }
            return this.updateStatus(issueList);

        } catch (IssueDataNotFound | RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

}
