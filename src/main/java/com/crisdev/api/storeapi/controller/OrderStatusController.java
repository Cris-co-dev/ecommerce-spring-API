package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.persistence.entity.OrderStatus;
import com.crisdev.api.storeapi.service.OrderStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }
    @GetMapping
    public ResponseEntity<List<OrderStatus>> readAvailableOrderStatuses(){
        return ResponseEntity.ok(orderStatusService.readAvailableOrderStatuses());
    }

}
