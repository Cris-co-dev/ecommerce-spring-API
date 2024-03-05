package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.CountryRequest;
import com.crisdev.api.storeapi.persistence.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> readAll();
    Country readById(Long countryId);
    Country addCountry(CountryRequest countryRequest);
    Country updateCountry(Long id, CountryRequest countryRequest);
    void deleteCountry(Long id);
}
