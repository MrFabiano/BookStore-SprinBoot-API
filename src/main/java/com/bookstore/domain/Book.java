package com.bookstore.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "required TITLE field")
    @Length(min = 3, max = 50, message = "TITLE field must be between 3 and 50 characters")
    private String title;

    @NotEmpty(message = "required TEXT field")
    @Length(min = 8, max = 2000000, message = "TEXT field must be between 8 and 2.000.000 characters")
    private String text;

    @NotEmpty(message = "required NAME AUTHOR field")
    @Length(min = 3, max = 50, message = "NAME AUTHOR  field must be between 3 and 50 characters")
    private String name_author;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {}

    public Book(Integer id, String title, String text, String name_author, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.name_author = name_author;
        this.category = category;
    }
}