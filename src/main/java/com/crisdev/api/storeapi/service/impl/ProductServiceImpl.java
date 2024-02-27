package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.ProductItemRequest;
import com.crisdev.api.storeapi.dto.request.ProductRequest;
import com.crisdev.api.storeapi.dto.response.ProductItemResponse;
import com.crisdev.api.storeapi.dto.response.ProductResponse;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.Category;
import com.crisdev.api.storeapi.persistence.entity.Product;
import com.crisdev.api.storeapi.persistence.entity.ProductItem;
import com.crisdev.api.storeapi.persistence.repository.CategoryRepository;
import com.crisdev.api.storeapi.persistence.repository.ProductItemRepository;
import com.crisdev.api.storeapi.persistence.repository.ProductRepository;
import com.crisdev.api.storeapi.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Product.ProductStatus DEFAULT_STATUS = Product.ProductStatus.ENABLED;

    private final CategoryRepository categoryRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductItemRepository productItemRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productItemRepository = productItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createproduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id: " + productRequest.getCategoryId()));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);
        product.setDefaultProductImageUrl(productRequest.getDefaultProductImageUrl());
        product.setBasePrice(productRequest.getBasePrice());
        product.setStatus(DEFAULT_STATUS);

        Product newProduct = productRepository.save(product);

        Set<ProductItem> newItems = productRequest.getProductItems().stream()
                .map(productItem -> dtoToEntity(productItem, newProduct))
                .collect(Collectors.toSet());
        newProduct.setProductItems(newItems);

        return productEntityToResponse(newProduct);
    }

    @Override
    public Page<ProductResponse> readAllProducts(Pageable pageable) {
        return productRepository.findAllByStatusEquals(DEFAULT_STATUS, pageable)
                .map(this::productEntityToResponse);
    }

    @Override
    public ProductResponse readProductById(Long id) {
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + id));

        return productEntityToResponse(productFromDB);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id:" + id));

        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + id));

        productFromDB.setName(productRequest.getName());
        productFromDB.setDescription(productRequest.getDescription());
        productFromDB.setCategory(category);
        productFromDB.setDefaultProductImageUrl(productRequest.getDefaultProductImageUrl());
        productFromDB.setBasePrice(productRequest.getBasePrice());

        Product updatedProduct = productRepository.save(productFromDB);

        Set<ProductItem> updatedItems = productRequest.getProductItems().stream()
                .map(productItem -> dtoToEntity(productItem, updatedProduct))
                .collect(Collectors.toSet());
        updatedProduct.setProductItems(updatedItems);

        return productEntityToResponse(updatedProduct);
    }

    @Override
    public Page<ProductResponse> findByBasePriceLessThan(BigDecimal price, Pageable pageable) {
        return productRepository
                .findByBasePriceLessThanAndStatusEquals(price, DEFAULT_STATUS, pageable)
                .map(this::productEntityToResponse);
    }

    @Override
    public Page<ProductResponse> findByBasePriceBetween(BigDecimal min, BigDecimal max, Pageable pageable) {
        return productRepository
                .findByBasePriceBetweenAndStatusEquals(min, max, DEFAULT_STATUS, pageable)
                .map(this::productEntityToResponse);
    }

    @Override
    public Page<ProductResponse> findByNameLikeIgnoreCase(String name, Pageable pageable) {
        return productRepository.findByNameLikeIgnoreCaseAndStatusEquals(name, DEFAULT_STATUS, pageable)
                .map(this::productEntityToResponse);
    }

    @Override
    public ProductResponse enableProduct(Long id) {
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + id));

        productFromDB.setStatus(DEFAULT_STATUS);

        return productEntityToResponse(productRepository.save(productFromDB));
    }

    @Override
    public ProductResponse disabledProduct(Long id) {
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + id));

        productFromDB.setStatus(Product.ProductStatus.DISABLED);

        return productEntityToResponse(productRepository.save(productFromDB));
    }
    @Override
    public void delete(Long id) {
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + id));

        productRepository.delete(productFromDB);
    }

    private ProductResponse productEntityToResponse(Product newProduct) {
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(newProduct, productResponse);
        productResponse.setStatus(newProduct.getStatus().name());
        productResponse.setProductItems(newProduct.getProductItems().stream()
                .map(this::productItemEntityToResponse)
                .collect(Collectors.toSet()));
        return productResponse;
    }

    private ProductItemResponse productItemEntityToResponse(ProductItem productItem) {
        ProductItemResponse productItemResponse = new ProductItemResponse();
        BeanUtils.copyProperties(productItem, productItemResponse);
        return productItemResponse;
    }

    private ProductItem dtoToEntity(ProductItemRequest productItem, Product newProduct) {
        ProductItem item;

        if (productItem.getId() != null) {
            item = productItemRepository.findById(productItem.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("ProductItem not found with id: " + productItem.getId()));
        } else {
            item = new ProductItem();
        }

        item.setProduct(newProduct);
        item.setQuantityInStock(productItem.getQuantityInStock());
        item.setColor(productItem.getColor());
        item.setMaterial(productItem.getMaterial());
        item.setPrice(productItem.getPrice());
        item.setProductImageUrl(productItem.getProductImageUrl());
        item.setSize(productItem.getSize());

        return productItemRepository.save(item);
    }


}
