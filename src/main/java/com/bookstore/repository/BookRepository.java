package com.bookstore.repository;

import com.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT obj FROM Book obj WHERE obj.category.id = :idCat ORDER BY title")
    List<Book> findAllByCategory(@Param(value = "idCat") Integer idCat);
}
