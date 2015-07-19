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
    private final String geonameId;
    private final String wikipedia;

    public City(String geonameId, String name, String country, MapPosition mapPosition, String wikipedia) {
        this.geonameId = geonameId;
        this.mapPosition = mapPosition;
        this.name = name;
        this.country = country;
        this.wikipedia = wikipedia;
    }

    public City() {
        this("", "", "", new MapPosition(0d, 0d), "");
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

    public String getGeonameId() {
        return geonameId;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        City city = (City) o;

        if (name != null ? !name.equals(city.name) : city.name != null) {
            return false;
        }
        if (mapPosition != null ? !mapPosition.equals(city.mapPosition) : city.mapPosition != null) {
            return false;
        }
        return !(country != null ? !country.equals(city.country) : city.country != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mapPosition != null ? mapPosition.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
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
