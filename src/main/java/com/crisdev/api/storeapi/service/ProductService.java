package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.ProductRequest;
import com.crisdev.api.storeapi.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {
    ProductResponse createproduct(ProductRequest productRequest);

    Page<ProductResponse> readAllProducts(Pageable pageable);

    ProductResponse readProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    Page<ProductResponse> findByBasePriceLessThan(BigDecimal price, Pageable pageable);

    Page<ProductResponse> findByBasePriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<ProductResponse> findByNameLikeIgnoreCase(String name, Pageable pageable);

    ProductResponse disabledProduct(Long productId);

    ProductResponse enableProduct(Long productId);

    void delete(Long id);

}
