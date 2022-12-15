package com.example.soap.util;

import io.spring.guides.gs_producing_web_service.City;
import io.spring.guides.gs_producing_web_service.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
    public static Country createCountryFromResultSet(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setCountryName(resultSet.getString("country_name"));
        return country;
    }

    public static City createCityFromResultSet(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt("id"));
        city.setCountryId(resultSet.getInt("country_id"));
        city.setCityName(resultSet.getString("city_name"));
        city.setPopulation(resultSet.getInt("population"));
        city.setIsCapital(resultSet.getBoolean("is_capital"));
        return city;
    }
}
