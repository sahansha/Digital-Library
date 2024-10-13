package com.library.e_library.exception;

import com.library.e_library.Model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handlerForAllException(Exception ex, WebRequest request)throws Exception
    {
        ErrorDetails errorDetails=ErrorDetails.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handlerForUserException(UserNotFoundException ex, WebRequest request)throws Exception
    {
        ErrorDetails errorDetails=ErrorDetails.builder()
                                            .timeStamp(LocalDateTime.now())
                                            .message(ex.getMessage())
                                            .description(request.getDescription(false))
                                            .build();
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFountException.class)
    public final ResponseEntity<ErrorDetails> handlerForBookNotFound(BookNotFountException ex, WebRequest request)throws Exception
    {
        ErrorDetails errorDetails=ErrorDetails.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails=ErrorDetails.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getFieldErrors().getFirst().getDefaultMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
