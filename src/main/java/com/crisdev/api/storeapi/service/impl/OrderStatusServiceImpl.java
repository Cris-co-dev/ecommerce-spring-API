package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.persistence.entity.OrderStatus;
import com.crisdev.api.storeapi.persistence.repository.OrderStatusRepository;
import com.crisdev.api.storeapi.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatus> readAvailableOrderStatuses() {

        List<OrderStatus> orderStatuses = new ArrayList<>();

        orderStatusRepository.findAll().forEach(orderStatuses::add);

        return orderStatuses;
    }

}
