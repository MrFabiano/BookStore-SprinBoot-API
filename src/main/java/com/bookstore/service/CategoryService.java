package com.bookstore.service;

import com.bookstore.domain.Category;
import com.bookstore.dtos.CategoryDTO;
import com.bookstore.exception.DataIntegrationViolationException;
import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Category> dtoList(){
        return categoryRepository.findAll();
    }

    public Category create(Category obj){
        obj.setId(null);
        return categoryRepository.save(obj);
    }

    public Category update(Integer id, CategoryDTO categoryDTO){
        Category category = searchById(id);
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        searchById(id);
        try {
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrationViolationException("Object cannot be deleted, as it has associated books");
        }
    }

}
