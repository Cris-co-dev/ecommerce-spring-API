package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.CategoryRequest;
import com.crisdev.api.storeapi.dto.response.CategoryResponse;
import com.crisdev.api.storeapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAll(Pageable pageable) {

        Page<CategoryResponse> categoriesPage = categoryService.findAllByStatusEnabled(pageable);

        if (categoriesPage.hasContent()) {
            return ResponseEntity.ok(categoriesPage);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {

        CategoryResponse category = categoryService.saveOneCategory(categoryRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategoryById(@RequestBody @Valid CategoryRequest categoryRequest,
                                                               @PathVariable Long categoryId) {
        CategoryResponse categoryResponse = categoryService.updateCategory(categoryId, categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<CategoryResponse> disableOneCategory (@PathVariable Long categoryId){

        CategoryResponse categoryResponse = categoryService.disableCategory(categoryId);

        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{categoryId}/enable")
    public ResponseEntity<CategoryResponse> enableOneCategory (@PathVariable Long categoryId){

        CategoryResponse categoryResponse = categoryService.enableCategory(categoryId);

        return ResponseEntity.ok(categoryResponse);
    }


}
