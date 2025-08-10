package com.telusko.joblisting.controller;

import com.telusko.joblisting.model.Book;
import com.telusko.joblisting.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/high-rated-technology")
    public List<Book> getHighRatedTechnologyBooks() {
        return bookService.getHighRatedTechnologyBooks();
    }
}
