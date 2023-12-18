package com.bookstore.service;

import com.bookstore.domain.Book;
import com.bookstore.domain.Category;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public void instanciaBancoDados(){
        Category category = new Category(null, "Computing", "Book T.I");
        Category category1 = new Category(null, "Computing", "Books");
        Category category2 = new Category(null, "Computing", "Books face");

        Book book = new Book(null, "Clean code", "Teixeira", "Java", category);
        Book book1 = new Book(null, "Book Java", "Java Records", "Java", category);
        Book book2 = new Book(null, "Clean Angular", "Angular Records", "Angular", category);

        category.getBooks().add(book);
        category1.getBooks().addAll(List.of(book1, book2));

        categoryRepository.saveAll(List.of(category,category1, category2));
        bookRepository.saveAll(List.of(book, book1, book2));


    }
}
