package com.senacor.geodata.model;

import java.io.Serializable;

/**
 * Bean representing a city.
 *
 * @author dschmitz
 */
public class City implements Serializable {
    private final String name;
    private final MapPosition mapPosition;
    private final String country;

    public City(String name, String country, MapPosition mapPosition) {
        this.mapPosition = mapPosition;
        this.name = name;
        this.country = country;
    }

    public MapPosition getMapPosition() {
        return mapPosition;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "City{" +
                "mapPosition=" + mapPosition +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
