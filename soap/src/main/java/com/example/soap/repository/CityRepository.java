package com.example.soap.repository;

import io.spring.guides.gs_producing_web_service.City;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityRepository {
    private final DataAccessObject dao;

    @Autowired
    public CityRepository(DataAccessObject dao) {
        this.dao = dao;
    }

    @PreDestroy
    public void onDestroy() {
        dao.closeConnection();
    }

    public City findCityById(int id) {
        return dao.findCityById(id)
                .orElseThrow(() -> new IllegalStateException("City not found"));
    }

    public void createCity(City city) {
        dao.createCity(city);
    }

    public void updateCity(City city) {
        dao.updateCity(city);
    }

    public void deleteCity(int id) {
        dao.deleteCity(id);
    }
}
