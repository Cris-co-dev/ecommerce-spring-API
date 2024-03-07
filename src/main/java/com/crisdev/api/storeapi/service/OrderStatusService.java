package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.persistence.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> readAvailableOrderStatuses();

}
