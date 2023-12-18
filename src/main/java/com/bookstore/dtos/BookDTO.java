package com.bookstore.dtos;

import com.bookstore.domain.Book;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BookDTO {

    private Integer id;
    private String title;

    public BookDTO (){
        super();
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }
}
