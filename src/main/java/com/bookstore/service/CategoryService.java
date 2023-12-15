package com.bookstore.service;

import com.bookstore.domain.Category;
import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category searchById(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return  category.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found id" + id + ", Type: " + Category.class.getName()));
    }
}
