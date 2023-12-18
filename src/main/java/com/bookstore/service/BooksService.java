package com.bookstore.service;

import com.bookstore.domain.Book;
import com.bookstore.domain.Category;
import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Book> findAllBooks(Integer id_category) {
        categoryService.searchById(id_category);
        return bookRepository.findAllByCategory(id_category);
    }

    public Book updateBook(Integer id, Book book) {
        Book newBook = findById(id);
        updateData(newBook, book);
        return bookRepository.save(newBook);
    }

    private void updateData(Book newBook, Book book) {
        newBook.setTitle(book.getTitle());
        newBook.setName_author(book.getName_author());
        newBook.setText(book.getText());
    }

    public Book create(Integer idCategory, Book book) {
        book.setId(null);
        Category cat = categoryService.searchById(idCategory);
        book.setCategory(cat);
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }
}
