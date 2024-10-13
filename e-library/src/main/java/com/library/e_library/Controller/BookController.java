package com.library.e_library.Controller;

import com.library.e_library.Model.Book;
import com.library.e_library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = {"/{id}"},produces ="application/json")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id)
    {
        Book book=this.bookService.getBookById(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

}
