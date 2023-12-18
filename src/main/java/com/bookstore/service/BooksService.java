package com.bookstore.service;

import com.bookstore.domain.Book;
import com.bookstore.dtos.BookDTO;
import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryService categoryService;

    public Book findById(Integer id){
        Optional<Book> opt = bookRepository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException("Object not found id" + id + ", Type: "
                + Book.class.getName()));
    }

    public List<Book> findAllBooks(Integer idCat) {
        categoryService.searchById(idCat);
        return bookRepository.findAllByCategory(idCat);
    }
}
