package com.bookstore.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "required NAME field")
    @Length(min = 3, max = 100, message = "NAME field must be between 3 and 100 characters")
    private String name;
    @NotEmpty(message = "required DESCRIPTION field")
    @Length(min = 3, max = 200, message = "DESCRIPTION field must be between 3 and 200 characters")
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public Category() {}

    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}