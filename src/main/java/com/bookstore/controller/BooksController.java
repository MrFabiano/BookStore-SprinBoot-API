package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.bookstore.dtos.BookDTO;
import com.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAllBooks(@RequestParam(value = "category", defaultValue = "0") Integer id_category){
        List<Book> list = booksService.findAllBooks(id_category);
        List<BookDTO> dtoList = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }
}
