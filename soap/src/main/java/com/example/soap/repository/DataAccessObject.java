package com.example.soap.repository;

import com.example.soap.util.Utils;
import io.spring.guides.gs_producing_web_service.City;
import io.spring.guides.gs_producing_web_service.Country;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Optional;

@Component
public class DataAccessObject {
    private static final String URL = "jdbc:mysql://localhost:3306/lab_9";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath", e);
        }
    }

    public DataAccessObject() {
        this.connection = connectToDatabase();
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connectToDatabase() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCountry(Country country) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO country (id, country_name) VALUES (?, ?)");
            ps.setInt(1, country.getId());
            ps.setString(2, country.getCountryName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCity(City city) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO city (id, country_id, city_name, population, is_capital) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, city.getId());
            ps.setInt(2, city.getCountryId());
            ps.setString(3, city.getCityName());
            ps.setInt(4, city.getPopulation());
            ps.setBoolean(5, city.isIsCapital());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCountry(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM country WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCity(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM city WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCountry(Country country) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE country SET country_name = ? WHERE id = ?");
            ps.setString(1, country.getCountryName());
            ps.setInt(2, country.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCity(City city) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE city SET country_id = ?, city_name = ?, population = ?, is_capital = ? WHERE id = ?");
            ps.setInt(1, city.getCountryId());
            ps.setString(2, city.getCityName());
            ps.setInt(3, city.getPopulation());
            ps.setBoolean(4, city.isIsCapital());
            ps.setInt(5, city.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Country> findCountryById(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM country WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next())
                return Optional.of(Utils.createCountryFromResultSet(resultSet));
            else
                return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<City> findCityById(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM city WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next())
                return Optional.of(Utils.createCityFromResultSet(resultSet));
            else
                return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
