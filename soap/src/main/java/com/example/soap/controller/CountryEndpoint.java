package com.example.soap.controller;

import com.example.soap.repository.CountryRepository;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountryById(request.getId()));
        return response;
    }

    @PayloadRoot(localPart = "createCountryRequest")
    @ResponsePayload
    public void createCountry(@RequestPayload CreateCountryRequest request) {
        countryRepository.createCountry(request.getCountry());
    }

    @PayloadRoot(localPart = "updateCountryRequest")
    @ResponsePayload
    public void updateCountry(@RequestPayload UpdateCountryRequest request) {
        countryRepository.updateCountry(request.getCountry());
    }

    @PayloadRoot(localPart = "deleteCountryRequest")
    @ResponsePayload
    public void deleteCountry(@RequestPayload DeleteCountryRequest request) {
        countryRepository.deleteCountry(request.getId());
    }
}
