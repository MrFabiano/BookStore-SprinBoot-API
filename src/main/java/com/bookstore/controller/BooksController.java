package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Integer id){
        Book book = booksService.findById(id);
        return ResponseEntity.ok().body(book);
    }
}
