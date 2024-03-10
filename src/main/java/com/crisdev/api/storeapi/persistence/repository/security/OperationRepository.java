package com.crisdev.api.storeapi.persistence.repository.security;

import com.crisdev.api.storeapi.persistence.entity.security.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Long> {

    @Query("select o from Operation o where o.permitAll = true")
    List<Operation> findByPublicAccess();

}
