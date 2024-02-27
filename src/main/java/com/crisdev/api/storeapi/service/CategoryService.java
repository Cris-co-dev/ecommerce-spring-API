package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.CategoryRequest;
import com.crisdev.api.storeapi.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryResponse> findAllByStatusEnabled(Pageable pageable);

    CategoryResponse saveOneCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);
    CategoryResponse disableCategory(Long categoryId);
    CategoryResponse enableCategory(Long categoryId);
}
