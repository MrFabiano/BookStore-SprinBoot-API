package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.bookstore.dtos.BookDTO;
import com.bookstore.service.BooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
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
    public ResponseEntity<List<BookDTO>> findAllBooks(@RequestParam(value = "category", defaultValue = "1") Integer id_category){
        List<Book> list = booksService.findAllBooks(id_category);
        List<BookDTO> dtoList = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id")Integer id, @Valid @RequestBody Book book){
       Book upBook =  booksService.updateBook(id, book);
       return ResponseEntity.ok().body(upBook);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Book> updateBookPath(@PathVariable("id")Integer id, @Valid @RequestBody Book book){
        Book upBook =  booksService.updateBook(id, book);
        return ResponseEntity.ok().body(upBook);

    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestParam(value = "category", defaultValue = "0")
                                           Integer id_category, @Valid @RequestBody Book book){

        Book createBook = booksService.create(id_category, book);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}")
                .buildAndExpand(createBook.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id){
        booksService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
