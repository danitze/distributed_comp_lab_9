package com.example.soap.repository;

import io.spring.guides.gs_producing_web_service.Country;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryRepository {
    private final DataAccessObject dao;

    @Autowired
    public CountryRepository(DataAccessObject dao) {
        this.dao = dao;
    }

    @PreDestroy
    public void onDestroy() {
        dao.closeConnection();
    }

    public Country findCountryById(int id) {
        return dao.findCountryById(id)
                .orElseThrow(() -> new IllegalStateException("Country not found"));
    }

    public void createCountry(Country country) {
        dao.createCountry(country);
    }

    public void updateCountry(Country country) {
        dao.updateCountry(country);
    }

    public void deleteCountry(int id) {
        dao.deleteCountry(id);
    }
}
