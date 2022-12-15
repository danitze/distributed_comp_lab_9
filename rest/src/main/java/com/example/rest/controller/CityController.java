package com.example.rest.controller;

import com.example.rest.model.City;
import com.example.rest.repository.DataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/city")
@RestController
public class CityController {
    private final DataAccessObject dataAccessObject;

    @Autowired
    public CityController(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    @GetMapping
    public ResponseEntity<City> getCityById(int id) {
        City city = dataAccessObject
                .findCityById(id)
                .orElseThrow(() -> new IllegalStateException("City not found"));
        return ResponseEntity.ok(city);
    }

    @PostMapping
    public ResponseEntity<City> createCity(City city) {
        dataAccessObject.createCity(city);
        return ResponseEntity.ok(city);
    }

    @PutMapping
    public ResponseEntity<City> updateCity(City city) {
        dataAccessObject.updateCity(city);
        return ResponseEntity.ok(city);
    }

    @DeleteMapping
    public ResponseEntity<City> deleteCity(Integer id) {
        dataAccessObject.deleteCity(id);
        return ResponseEntity.ok().build();
    }
}
