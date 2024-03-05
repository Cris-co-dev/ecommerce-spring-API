package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
}
