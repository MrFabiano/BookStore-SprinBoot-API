package com.bookstore.service;

import com.bookstore.domain.Book;
import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BookRepository bookRepository;

    public Book findById(Integer id){
        Optional<Book> opt = bookRepository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException("Object not found id" + id + ", Type: "
                + Book.class.getName()));
    }
}
