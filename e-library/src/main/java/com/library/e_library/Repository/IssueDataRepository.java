package com.library.e_library.Repository;

import com.library.e_library.Model.IssueData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IssueDataRepository extends JpaRepository<IssueData, UUID> {


    @Query("select i from IssueData i where i.member.id = ?1")
    List<IssueData> findByMemberId(UUID id);
}
