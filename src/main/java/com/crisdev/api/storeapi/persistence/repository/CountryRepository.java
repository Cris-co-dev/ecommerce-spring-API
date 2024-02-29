package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
