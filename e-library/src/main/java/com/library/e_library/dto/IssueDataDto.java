package com.library.e_library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueDataDto {
    public UUID memberId;
    public UUID bookId;
}
