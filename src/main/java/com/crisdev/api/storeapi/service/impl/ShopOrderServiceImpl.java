package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.ShopOrderRequest;
import com.crisdev.api.storeapi.dto.response.AddressResponse;
import com.crisdev.api.storeapi.dto.response.OrderLineResponse;
import com.crisdev.api.storeapi.dto.response.ShopOrderResponse;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.exception.OrderTotalException;
import com.crisdev.api.storeapi.exception.ShoppingCartEmptyException;
import com.crisdev.api.storeapi.persistence.entity.*;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.*;
import com.crisdev.api.storeapi.persistence.repository.security.UserRepository;
import com.crisdev.api.storeapi.service.ShopOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class ShopOrderServiceImpl implements ShopOrderService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final AddressRepository addressRepository;
    private final UserPaymentMethodRepository userPaymentMethodRepository;
    private final ShippingMethodRepository shippingMethodRepository;
    private final ShopOrderRepository shopOrderRepository;
    private final OrderLineRepository orderLineRepository;

    public ShopOrderServiceImpl(UserRepository userRepository,
                                ShoppingCartRepository shoppingCartRepository,
                                OrderStatusRepository orderStatusRepository,
                                AddressRepository addressRepository,
                                UserPaymentMethodRepository userPaymentMethodRepository,
                                ShippingMethodRepository shippingMethodRepository,
                                ShopOrderRepository shopOrderRepository,
                                OrderLineRepository orderLineRepository) {

        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressRepository = addressRepository;
        this.userPaymentMethodRepository = userPaymentMethodRepository;
        this.shippingMethodRepository = shippingMethodRepository;
        this.shopOrderRepository = shopOrderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public Page<ShopOrderResponse> readAllOrders(Long userId, Pageable pageable) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + userId));

        return shopOrderRepository.findAllByUser(user, pageable)
                .map(ShopOrderServiceImpl::shopOrderEntityToResponse);
    }

    @Override
    public ShopOrderResponse createOrder(ShopOrderRequest shopOrderRequest) {

        User user = userRepository.findById(shopOrderRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + shopOrderRequest.getUserId()));

        Address address = addressRepository.findById(shopOrderRequest.getShippingAddressId())
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id: " + shopOrderRequest.getShippingAddressId()));

        UserPaymentMethod userPaymentMethod = userPaymentMethodRepository.findById(shopOrderRequest.getPaymentMethodId())
                .orElseThrow(() -> new ObjectNotFoundException("Payment method not found with id: " + shopOrderRequest.getPaymentMethodId()));

        ShippingMethod shippingMethod = shippingMethodRepository.findById(shopOrderRequest.getShippingMethodId())
                .orElseThrow(() -> new ObjectNotFoundException("Shipping method not found with id: " + shopOrderRequest.getShippingMethodId()));


        Long initialStatus = 1L;
        OrderStatus orderStatus = orderStatusRepository.findById(initialStatus)
                .orElseThrow(() -> new ObjectNotFoundException("Order status not found with id: " + initialStatus));


        ShoppingCart cart = shoppingCartRepository.findByUser(user);
        Set<ShoppingCartItem> shoppingCartItems = cart.getShoppingCartItems();

        if (shoppingCartItems.isEmpty()) {
            throw new ShoppingCartEmptyException("The shopping cart is empty");
        }

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setId(UUID.randomUUID());
        shopOrder.setUser(user);
        shopOrder.setAddress(address);
        shopOrder.setOrderStatus(orderStatus);
        shopOrder.setPaymentMethod(userPaymentMethod);
        shopOrder.setShippingMethod(shippingMethod);
        shopOrder.setOrderDate(LocalDateTime.now());

        List<OrderLine> orderLines = new ArrayList<>();

        shoppingCartItems.forEach(item -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setPrice(item.getProductItem().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            orderLine.setQuantity(item.getQuantity());
            orderLine.setProductItem(item.getProductItem());
            orderLine.setShopOrder(shopOrder);

            orderLines.add(orderLineRepository.save(orderLine));
        });

        shopOrder.setOrders(orderLines);


        BigDecimal orderTotal = shoppingCartItems.stream()
                .map(item -> item.getProductItem().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .map(total -> total.add(shippingMethod.getPrice()))
                .orElseThrow(() -> new OrderTotalException("Error cannot calculate the order total"));

        shopOrder.setOrderTotal(orderTotal);

        ShopOrder savedShopOrder = shopOrderRepository.save(shopOrder);

        return shopOrderEntityToResponse(savedShopOrder);
    }

    @Override
    public ShopOrderResponse updateOrderStatus(UUID orderId, Long orderStatusId) {
        ShopOrder order = shopOrderRepository.findById(orderId);

        OrderStatus orderStatus = orderStatusRepository.findById(orderStatusId)
                .orElseThrow(() -> new ObjectNotFoundException("Order status not found with id:" + orderStatusId));

        if (Objects.equals(order.getOrderStatus().getId(), orderStatus.getId())) {
            return shopOrderEntityToResponse(order);
        }

        order.setOrderStatus(orderStatus);

        ShopOrder updatedOrder = shopOrderRepository.save(order);

        return shopOrderEntityToResponse(updatedOrder);
    }

    private static ShopOrderResponse shopOrderEntityToResponse(ShopOrder savedShopOrder) {
        ShopOrderResponse shopOrderResponse = new ShopOrderResponse();
        BeanUtils.copyProperties(savedShopOrder, shopOrderResponse);
        shopOrderResponse.setOrderStatus(savedShopOrder.getOrderStatus().getStatus());
        shopOrderResponse.setTotal(savedShopOrder.getOrderTotal());
        shopOrderResponse.setOrderDetails(orderLineEntityToResponse(savedShopOrder.getOrders()));

        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(savedShopOrder.getAddress(), addressResponse);
        addressResponse.setAddressId(savedShopOrder.getAddress().getId());
        addressResponse.setCountryId(savedShopOrder.getAddress().getCountry().getId());
        addressResponse.setUserId(savedShopOrder.getAddress().getUser().getId());

        shopOrderResponse.setAddressResponse(addressResponse);
        return shopOrderResponse;
    }

    private static List<OrderLineResponse> orderLineEntityToResponse(List<OrderLine> orders) {
        List<OrderLineResponse> orderLines = new ArrayList<>();

        orders.forEach(orderLine -> {
            OrderLineResponse orderLineResponse = new OrderLineResponse();
            orderLineResponse.setProductItemId(orderLine.getProductItem().getId());
            orderLineResponse.setProductImageUrl(orderLine.getProductItem().getProductImageUrl());
            orderLineResponse.setSize(orderLine.getProductItem().getSize());
            orderLineResponse.setColor(orderLine.getProductItem().getColor());
            orderLineResponse.setMaterial(orderLine.getProductItem().getMaterial());
            orderLineResponse.setQuantity(orderLine.getQuantity());
            orderLineResponse.setPrice(orderLine.getPrice());
            orderLines.add(orderLineResponse);
        });

        return orderLines;
    }
}
