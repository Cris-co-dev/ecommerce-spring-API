package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
}
