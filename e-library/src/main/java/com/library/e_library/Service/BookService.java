package com.library.e_library.Service;

import com.library.e_library.Model.Book;
import com.library.e_library.Repository.BookRepository;
import com.library.e_library.exception.BookNotFountException;
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
        try{
            return this.bookRepository.save(book);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
    public List<Book> getAllBooks()
    {
        try{
            return this.bookRepository.findAll();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public Book getBookById(UUID id)
    {
        try{
            Book book= this.bookRepository.findById(id).orElse(null);
            if(book==null)
            {
                throw new BookNotFountException(String.format("Book with id : %s is not available",id));
            }
            return book;
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }


}
