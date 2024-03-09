package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.ChangeQuantityRequest;
import com.crisdev.api.storeapi.dto.request.DeleteItemRequest;
import com.crisdev.api.storeapi.dto.request.ShoppingCartItemRequest;
import com.crisdev.api.storeapi.dto.request.ShoppingCartRequest;
import com.crisdev.api.storeapi.dto.response.ShoppingCartItemResponse;
import com.crisdev.api.storeapi.dto.response.ShoppingCartResponse;
import com.crisdev.api.storeapi.dto.response.util.CartItem;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.ProductItem;
import com.crisdev.api.storeapi.persistence.entity.ShoppingCart;
import com.crisdev.api.storeapi.persistence.entity.ShoppingCartItem;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.ProductItemRepository;
import com.crisdev.api.storeapi.persistence.repository.ShoppingCartItemRepository;
import com.crisdev.api.storeapi.persistence.repository.ShoppingCartRepository;
import com.crisdev.api.storeapi.persistence.repository.security.UserRepository;
import com.crisdev.api.storeapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductItemRepository productItemRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartServiceImpl(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, ProductItemRepository productItemRepository, ShoppingCartItemRepository shoppingCartItemRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productItemRepository = productItemRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    @Override
    public ShoppingCartItemResponse addProductToCart(ShoppingCartItemRequest shoppingCartRequest) {
        User user = userRepository.findById(shoppingCartRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + shoppingCartRequest.getUserId()));

        ProductItem item = productItemRepository.findById(shoppingCartRequest.getProductItemId())
                .orElseThrow(() -> new ObjectNotFoundException("ProductItem not found with id: " + shoppingCartRequest.getProductItemId()));

        ShoppingCart cart = shoppingCartRepository.findByUser(user);

        Optional<ShoppingCartItem> any = cart.getShoppingCartItems()
                .stream()
                .filter(cartItem -> Objects.equals(cartItem.getProductItem().getId(), item.getId()))
                .findAny();

        if (any.isPresent()) {
           return entityToResponse("This product is already in the cart!", any.get());
        }

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setShoppingCart(cart);
        shoppingCartItem.setProductItem(item);
        shoppingCartItem.setQuantity(shoppingCartRequest.getQuantity());

        ShoppingCartItem savedItem = shoppingCartItemRepository.save(shoppingCartItem);
        shoppingCartRepository.save(cart);

        return entityToResponse("Product added to cart successfully", savedItem);
    }

    @Override
    public ShoppingCartResponse readCart(ShoppingCartRequest shoppingCartRequest) {

        User user = userRepository.findById(shoppingCartRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + shoppingCartRequest.getUserId()));

        ShoppingCart cart = shoppingCartRepository.findByUser(user);

        return new ShoppingCartResponse(cart.getShoppingCartItems()
                .stream().map(this::entityToDto).collect(Collectors.toList()));
    }

    @Override
    public ShoppingCartItemResponse updateProductQuantity(Long productItemId, ChangeQuantityRequest changeQuantityRequest) {

        User user = userRepository.findById(changeQuantityRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + changeQuantityRequest.getUserId()));

        ShoppingCart cart = shoppingCartRepository.findByUser(user);

        ShoppingCartItem shoppingCartItem = cart.getShoppingCartItems().stream()
                .filter(item -> Objects.equals(item.getId(), productItemId))
                .findAny()
                .orElseThrow(() -> new ObjectNotFoundException("ProductItem not found with id: " + productItemId));

        shoppingCartItem.setQuantity(changeQuantityRequest.getQuantity());

        ShoppingCartItem updatedItem = shoppingCartItemRepository.save(shoppingCartItem);

        return entityToResponse("Quantity updated successfully", updatedItem);
    }

    @Override
    public ShoppingCartItemResponse deleteProductItem(DeleteItemRequest deleteItemRequest) {

        Long userId = deleteItemRequest.getUserId();
        Long productItemId = deleteItemRequest.getProductItemId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userId));

        ShoppingCart cart = shoppingCartRepository.findByUser(user);

        ShoppingCartItem shoppingCartItem = cart.getShoppingCartItems()
                .stream()
                .filter(item -> Objects.equals(item.getProductItem().getId(), productItemId))
                .findAny()
                .orElseThrow(() -> new ObjectNotFoundException("ProductItem not found with id: " + productItemId));

        shoppingCartItemRepository.delete(shoppingCartItem);

        return entityToResponse("Product removed from cart successfully", shoppingCartItem);
    }

    @Override
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userId));

        ShoppingCart cart = shoppingCartRepository.findByUser(user);
        shoppingCartItemRepository.deleteAll(cart.getShoppingCartItems());

    }

    private CartItem entityToDto(ShoppingCartItem shoppingCartItem) {
        CartItem cartItem = new CartItem();
        cartItem.setId(shoppingCartItem.getProductItem().getId());
        cartItem.setName(shoppingCartItem.getProductItem().getProduct().getName());
        cartItem.setQuantity(shoppingCartItem.getQuantity());
        cartItem.setPrice(shoppingCartItem.getProductItem().getPrice()
                .multiply(BigDecimal.valueOf(shoppingCartItem.getQuantity())));
        return cartItem;

    }

    private ShoppingCartItemResponse entityToResponse(String message, ShoppingCartItem savedItem) {
        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
        shoppingCartItemResponse.setMessage(message);
        shoppingCartItemResponse.setQuantity(savedItem.getQuantity());
        shoppingCartItemResponse.setProductItemId(savedItem.getProductItem().getId());

        return shoppingCartItemResponse;
    }
}
