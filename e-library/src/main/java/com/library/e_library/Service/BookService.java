package com.library.e_library.Service;

import com.library.e_library.Model.Book;
import com.library.e_library.Repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book)
    {
      log.info("Saving a new book");
        Book savedBook= this.bookRepository.save(book);
        log.info("Saved a new Book with Id: {}",savedBook.getId());
        return savedBook;
    }
    public List<Book> getAllBooks()
    {
        return this.bookRepository.findAll();
    }
}
