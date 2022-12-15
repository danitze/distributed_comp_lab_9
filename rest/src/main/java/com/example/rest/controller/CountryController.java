package com.example.rest.controller;

import com.example.rest.model.Country;
import com.example.rest.repository.DataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/country")
@RestController
public class CountryController {
    private final DataAccessObject dataAccessObject;

    @Autowired
    public CountryController(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    @GetMapping
    public ResponseEntity<Country> getCountryById(int id) {
        Country country = dataAccessObject
                .findCountryById(id)
                .orElseThrow(() -> new IllegalStateException("Country not found"));
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(Country country) {
        dataAccessObject.createCountry(country);
        return ResponseEntity.ok(country);
    }

    @PutMapping
    public ResponseEntity<Country> updateCountry(Country country) {
        dataAccessObject.updateCountry(country);
        return ResponseEntity.ok(country);
    }

    @DeleteMapping
    public ResponseEntity<Country> deleteCountry(Integer id) {
        dataAccessObject.deleteCountry(id);
        return ResponseEntity.ok().build();
    }
}
