package com.example.rest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Country {
    private int id;
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {}

    public static Country createFromResultSet(ResultSet resultSet) throws SQLException {
        return new Country(resultSet.getInt("id"), resultSet.getString("country_name"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void copy(Country country) {
        this.name = country.name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
