package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByStatusEquals(Product.ProductStatus status, Pageable pageable);
    Page<Product> findByBasePriceLessThanAndStatusEquals(BigDecimal price, Product.ProductStatus status, Pageable pageable);
    Page<Product> findByBasePriceBetweenAndStatusEquals(BigDecimal min, BigDecimal max, Product.ProductStatus status, Pageable pageable);
    Page<Product> findByNameLikeIgnoreCaseAndStatusEquals(String name, Product.ProductStatus status, Pageable pageable);


}
