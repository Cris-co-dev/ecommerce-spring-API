package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.ShippingMethodRequest;
import com.crisdev.api.storeapi.persistence.entity.ShippingMethod;

import java.util.List;

public interface ShippingMethodService {
    List<ShippingMethod> readAllShippingMethods();

    ShippingMethod readOneShippingMethods(Long id);

    ShippingMethod addShippingMethod(ShippingMethodRequest shippingMethodRequest);
}
