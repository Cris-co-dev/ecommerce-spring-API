package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.Category;
import com.crisdev.api.storeapi.persistence.entity.util.CategoryStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.status = :status")
    Page<Category> findAllByStatusEnabled(CategoryStatusEnum status, Pageable pageable);

}
