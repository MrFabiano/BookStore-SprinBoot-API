package com.bookstore.dtos;

import com.bookstore.domain.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CategoryDTO {

    private Integer id;
    private String name;
    private String description;

    public CategoryDTO(){
        super();
    }

    public CategoryDTO(Category obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.description = getDescription();
    }
}
