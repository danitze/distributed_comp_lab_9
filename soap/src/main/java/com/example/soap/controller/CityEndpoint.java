package com.example.soap.controller;

import com.example.soap.repository.CityRepository;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CityEndpoint {
    private final CityRepository cityRepository;

    @Autowired
    public CityEndpoint(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PayloadRoot(localPart = "getCityRequest")
    @ResponsePayload
    public GetCityResponse getCity(@RequestPayload GetCityRequest request) {
        GetCityResponse response = new GetCityResponse();
        response.setCity(cityRepository.findCityById(request.getId()));
        return response;
    }

    @PayloadRoot(localPart = "createCityRequest")
    @ResponsePayload
    public void createCity(@RequestPayload CreateCityRequest request) {
        cityRepository.createCity(request.getCity());
    }

    @PayloadRoot(localPart = "updateCityRequest")
    @ResponsePayload
    public void updateCity(@RequestPayload UpdateCityRequest request) {
        cityRepository.updateCity(request.getCity());
    }

    @PayloadRoot(localPart = "deleteCityRequest")
    @ResponsePayload
    public void deleteCity(@RequestPayload DeleteCityRequest request) {
        cityRepository.deleteCity(request.getId());
    }
}
