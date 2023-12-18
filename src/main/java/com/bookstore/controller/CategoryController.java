package com.bookstore.controller;


import com.bookstore.domain.Category;
import com.bookstore.dtos.CategoryDTO;
import com.bookstore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
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

    @PostMapping(value = "/")
    public ResponseEntity<Category> create(@Valid @RequestBody Category obj){
       obj = categoryService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDTO dto){
        Category category = categoryService.update(id, dto);
        return ResponseEntity.ok().body(new CategoryDTO(category));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
