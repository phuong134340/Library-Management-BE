package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateCategoryRequest;
import com.se104.librarymanagementbe.dto.request.UpdateCategoryRequest;
import com.se104.librarymanagementbe.dto.response.CreateCategoryResponse;
import com.se104.librarymanagementbe.dto.response.GetListCategoryResponse;
import com.se104.librarymanagementbe.dto.response.GetOneCategoryResponse;
import com.se104.librarymanagementbe.dto.response.UpdateCategoryResponse;
import com.se104.librarymanagementbe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController( CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<CreateCategoryResponse>> createCategory(@RequestBody CreateCategoryRequest category) {
        return ResponseEntity.ok().body(categoryService.createCategory(category));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<GetListCategoryResponse>>> getListCategory(){
        return ResponseEntity.ok().body(categoryService.getListCategory());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneCategoryResponse>> getOneCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.getOneCategory(id));
    }

    @PutMapping( "/{id}")
    public ResponseEntity<RestResponse<UpdateCategoryResponse>> updateCategory (@RequestBody UpdateCategoryRequest category, @PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.updateCategory(category,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory (@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
