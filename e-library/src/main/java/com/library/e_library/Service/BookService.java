package com.library.e_library.Service;

import com.library.e_library.Model.Book;
import com.library.e_library.Repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

    public Book getBookById(UUID id)
    {
        return this.bookRepository.findById(id).orElse(new Book());
    }


}
