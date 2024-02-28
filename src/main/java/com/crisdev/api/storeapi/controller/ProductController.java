package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.ProductRequest;
import com.crisdev.api.storeapi.dto.response.ProductResponse;
import com.crisdev.api.storeapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> readAllProducts(Pageable pageable) {

        Page<ProductResponse> products = productService.readAllProducts(pageable);

        if (products.hasContent()) {
            return ResponseEntity.ok(products);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> readProductById(@PathVariable Long productId) {

        ProductResponse productResponse = productService.readProductById(productId);
        return ResponseEntity.ok(productResponse);

    }

    @GetMapping("/less_price")
    public ResponseEntity<Page<ProductResponse>> readAllPriceLessThan(@RequestParam BigDecimal price,
                                                                      Pageable pageable) {

        Page<ProductResponse> responses = productService.findByBasePriceLessThan(price, pageable);

        return responses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
    }

    @GetMapping("/between_price")
    public ResponseEntity<Page<ProductResponse>> readByPriceMinAndMax(@RequestParam BigDecimal min,
                                                                      @RequestParam BigDecimal max,
                                                                      Pageable pageable) {
        Page<ProductResponse> responses = productService.findByBasePriceBetween(min, max, pageable);

        return responses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
    }

    @GetMapping("/name")
    public ResponseEntity<Page<ProductResponse>> readAllByNameLike(@RequestParam String name, Pageable pageable) {
        Page<ProductResponse> responses = productService.findByNameLikeIgnoreCase(name, pageable);
        return responses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        ProductResponse productResponse = productService.createproduct(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody @Valid ProductRequest productRequest,
                                                         @PathVariable Long productId) {

        ProductResponse productResponse = productService.updateProduct(productId, productRequest);

        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<ProductResponse> disableProduct(@PathVariable Long productId) {
        ProductResponse productResponse = productService.disabledProduct(productId);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{productId}/enable")
    public ResponseEntity<ProductResponse> enableProduct(@PathVariable Long productId) {
        ProductResponse productResponse = productService.enableProduct(productId);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
