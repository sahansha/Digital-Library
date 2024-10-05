package com.library.e_library.Controller;

import com.library.e_library.Model.Book;
import com.library.e_library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping(path = {"/add"},consumes = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook=bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping(value = {"/get"},produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> books=this.bookService.getAllBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

}
