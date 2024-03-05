package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.ShippingMethodRequest;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.ShippingMethod;
import com.crisdev.api.storeapi.persistence.repository.ShippingMethodRepository;
import com.crisdev.api.storeapi.service.ShippingMethodService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingMethodServiceImpl implements ShippingMethodService {

    private final ShippingMethodRepository shippingMethodRepository;

    public ShippingMethodServiceImpl(ShippingMethodRepository shippingMethodRepository) {
        this.shippingMethodRepository = shippingMethodRepository;
    }

    @Override
    public List<ShippingMethod> readAllShippingMethods() {
        List<ShippingMethod> methods = new ArrayList<>();
        Iterable<ShippingMethod> all = shippingMethodRepository.findAll();

        all.forEach(methods::add);

        return methods;
    }

    @Override
    public ShippingMethod readOneShippingMethods(Long id) {
        return shippingMethodRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Shipping method not found with id: " + id));
    }

    @Override
    public ShippingMethod addShippingMethod(ShippingMethodRequest shippingMethodRequest) {

        ShippingMethod shippingMethod = new ShippingMethod();
        shippingMethod.setName(shippingMethodRequest.getName());
        shippingMethod.setPrice(shippingMethodRequest.getPrice());

        ShippingMethod savedShippingMethod = shippingMethodRepository.save(shippingMethod);

        return savedShippingMethod;
    }
}
