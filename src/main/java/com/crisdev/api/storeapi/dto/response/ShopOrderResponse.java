package com.crisdev.api.storeapi.dto.response;

import com.crisdev.api.storeapi.persistence.entity.ShippingMethod;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ShopOrderResponse implements Serializable {
    private UUID id;
    private List<OrderLineResponse> orderDetails;
    private AddressResponse addressResponse;
    private ShippingMethod shippingMethod;
    private String orderStatus;
    private BigDecimal total;
    private LocalDateTime orderDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<OrderLineResponse> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderLineResponse> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public AddressResponse getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(AddressResponse addressResponse) {
        this.addressResponse = addressResponse;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
