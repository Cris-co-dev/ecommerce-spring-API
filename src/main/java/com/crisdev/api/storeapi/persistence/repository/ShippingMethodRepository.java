package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.ShippingMethod;
import org.springframework.data.repository.CrudRepository;

public interface ShippingMethodRepository extends CrudRepository<ShippingMethod, Long> {
}
