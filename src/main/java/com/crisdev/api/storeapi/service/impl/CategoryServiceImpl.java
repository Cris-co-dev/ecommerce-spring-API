package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.CategoryRequest;
import com.crisdev.api.storeapi.dto.response.CategoryResponse;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.Category;
import com.crisdev.api.storeapi.persistence.entity.util.CategoryStatusEnum;
import com.crisdev.api.storeapi.persistence.repository.CategoryRepository;
import com.crisdev.api.storeapi.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryResponse> findAllByStatusEnabled(Pageable pageable) {
        return categoryRepository.findAllByStatusEnabled(CategoryStatusEnum.ENABLED, pageable).map(this::entityToResponse);
    }

    @Override
    public CategoryResponse saveOneCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setStatus(CategoryStatusEnum.ENABLED);
        Category savedCategory = categoryRepository.save(category);
        return entityToResponse(savedCategory);
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {

        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category with id:" + categoryId + " not found."));
        categoryFromDB.setName(categoryRequest.getName());

        Category savedCategory = categoryRepository.save(categoryFromDB);

        return entityToResponse(savedCategory);
    }


    @Override
    public CategoryResponse disableCategory(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category with id:" + categoryId + " not found."));

        categoryFromDB.setStatus(CategoryStatusEnum.DISABLED);

        Category updatedCategory = categoryRepository.save(categoryFromDB);

        return entityToResponse(updatedCategory);
    }

    @Override
    public CategoryResponse enableCategory(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category with id:" + categoryId + " not found."));

        categoryFromDB.setStatus(CategoryStatusEnum.ENABLED);

        Category updatedCategory = categoryRepository.save(categoryFromDB);

        return entityToResponse(updatedCategory);
    }

    private CategoryResponse entityToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);
        categoryResponse.setStatus(category.getStatus().name());
        return categoryResponse;
    }
}
