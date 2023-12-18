package com.bookstore.dtos;

import com.bookstore.domain.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@EqualsAndHashCode
public class CategoryDTO {

    private Integer id;
    @NotEmpty(message = "required NAME field")
    @Length(min = 3, max = 100, message = "NAME field must be between 3 and 100 characters")
    private String name;
    @NotEmpty(message = "required DESCRIPTION field")
    @Length(min = 3, max = 200, message = "DESCRIPTION field must be between 3 and 200 characters")
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
