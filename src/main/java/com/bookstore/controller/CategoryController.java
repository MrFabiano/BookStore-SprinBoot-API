package com.bookstore.controller;


import com.bookstore.domain.Category;
import com.bookstore.dtos.CategoryDTO;
import com.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> searchId(@PathVariable("id")Integer id){
        Category category = categoryService.searchById(id);
        //return new ResponseEntity<>(category, HttpStatus.OK);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<CategoryDTO>> listCategoryDTO(){
        List<Category> category = categoryService.dtoList();
        List<CategoryDTO> categoryDTO = category.stream().map(obj ->
                new CategoryDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoryDTO);
    }
}
