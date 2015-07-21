package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Bean representing a city.
 *
 * @author dschmitz
 */
public class City implements Serializable {
    static final String BING_URL_PATTERN = "https://www.bing.com/maps/embed/viewer.aspx?v=3&cp=%s~%s&lvl=12&w=600&h=400&sty=r&typ=s&pp=&ps=55&dir=0&mkt=de-de&src=SHELL&form=BMEMJS";
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

    public String buildBingUrl() {
        return String.format(BING_URL_PATTERN, getMapPosition().getLatitute(), getMapPosition().getLongitude());
    }

    public String buildWikipediaUrl() {
        return "http://" + getWikipedia().replace("en.wikipedia", "en.m.wikipedia");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof City)) {
            return false;
        }

        City other = (City) o;
        return new EqualsBuilder()
                .append(getGeonameId(), other.getGeonameId())
                .append(getName(), other.getName())
                .append(getCountry(), other.getCountry())
                .append(getMapPosition(), other.getMapPosition())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getGeonameId()).append(getName()).append(getCountry()).append(getMapPosition()).toHashCode();
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
