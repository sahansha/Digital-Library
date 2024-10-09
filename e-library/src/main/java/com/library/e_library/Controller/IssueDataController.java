package com.library.e_library.Controller;

import com.library.e_library.Model.IssueData;
import com.library.e_library.Service.IssueDataService;
import com.library.e_library.dto.IssueDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue_data")
public class IssueDataController {

    private final IssueDataService issueDataService;

    @Autowired
    public IssueDataController(IssueDataService issueDataService) {
        this.issueDataService = issueDataService;
    }

    @PostMapping(path = {"/add"},consumes = {"application/json"})
    public ResponseEntity<IssueData> addIssueData(@RequestBody IssueDataDto issueDataDto)
    {
        IssueData addedData=this.issueDataService.addIssueData(issueDataDto);
        return new ResponseEntity<>(addedData, HttpStatus.CREATED);
    }

    @GetMapping(path = {"/member/books/{id}"})
    public ResponseEntity<List<IssueData>> getBooksByMemberId(@PathVariable UUID id)
    {
        List<IssueData> issueDataList=this.issueDataService.getIssueDataById(id);
        if(issueDataList!=null)
        {
            return new ResponseEntity<>(issueDataList,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = {"/status"})
    public ResponseEntity<String> updateMembersIssueStatus(@RequestParam UUID id)
    {
        String response=this.issueDataService.updateStatus(id);
        if(response.equals("updated"))
        {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
