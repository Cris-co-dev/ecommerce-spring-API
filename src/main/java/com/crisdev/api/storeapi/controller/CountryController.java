package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.CountryRequest;
import com.crisdev.api.storeapi.persistence.entity.Country;
import com.crisdev.api.storeapi.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> readAllCountries() {
        List<Country> countries = countryService.readAll();

        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> readOneCountry(@PathVariable Long countryId) {
        Country country = countryService.readById(countryId);

        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody @Valid CountryRequest countryRequest) {
        Country country = countryService.addCountry(countryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long countryId, @RequestBody @Valid CountryRequest countryRequest) {
        Country country = countryService.updateCountry(countryId, countryRequest);
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Map<String, String>> deleteCountry(@PathVariable Long countryId) {

        countryService.deleteCountry(countryId);

        return ResponseEntity.ok(Collections.singletonMap("message", "Country deleted successfully"));
    }

}
