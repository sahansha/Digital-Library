package com.library.e_library.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String description;
}
