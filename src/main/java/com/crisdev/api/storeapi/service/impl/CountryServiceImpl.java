package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.CountryRequest;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.Country;
import com.crisdev.api.storeapi.persistence.repository.CountryRepository;
import com.crisdev.api.storeapi.service.CountryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> readAll() {

        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);

        return countries;
    }

    @Override
    public Country addCountry(CountryRequest countryRequest) {
        return countryRepository.save(new Country(countryRequest.getName()));
    }

    @Override
    public Country updateCountry(Long id, CountryRequest countryRequest) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Country not found with id: " + id));

        country.setCountryName(countryRequest.getName());

        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Country not found with id: " + id));

        countryRepository.delete(country);
    }
}
