package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.ShopOrderRequest;
import com.crisdev.api.storeapi.dto.response.ShopOrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ShopOrderService {
    ShopOrderResponse createOrder(ShopOrderRequest shopOrderRequest);
    Page<ShopOrderResponse> readAllOrders(Long userId, Pageable pageable);
    ShopOrderResponse updateOrderStatus(UUID orderId, Long orderStatusId);

}
