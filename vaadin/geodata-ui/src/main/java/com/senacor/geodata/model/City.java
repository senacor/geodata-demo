package com.senacor.geodata.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Bean representing a city.
 *
 * @author dschmitz
 */
public class City implements Serializable {
    static final String BING_URL_PATTERN = "https://www.bing.com/maps/embed/viewer.aspx?v=3&cp=%s~%s&lvl=12&w=600&h=400&sty=r&typ=s&pp=&ps=55&dir=0&mkt=de-de&src=SHELL&form=BMEMJS";
    @NotNull
    private final String name;
    @NotNull
    private final SphericalCoordinates sphericalCoordinates;
    @NotNull
    private final String country;
    @NotNull
    private final String geonameId;
    @NotNull
    private final String wikipedia;

    public City(@Nonnull String geonameId, @Nonnull String name, @Nonnull String country, @Nonnull SphericalCoordinates sphericalCoordinates, String wikipedia) {
        this.geonameId = geonameId;
        this.sphericalCoordinates = sphericalCoordinates;
        this.name = name;
        this.country = country;
        this.wikipedia = wikipedia;
    }

    public City() {
        this("", "", "", new SphericalCoordinates(0d, 0d), "");
    }

    public SphericalCoordinates getSphericalCoordinates() {
        return sphericalCoordinates;
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
        return String.format(BING_URL_PATTERN, getSphericalCoordinates().getLatitute(), getSphericalCoordinates().getLongitude());
    }

    public String buildWikipediaUrl() {
        return "http://" + getWikipedia().replace("en.wikipedia", "en.m.wikipedia");
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "City{" +
                "sphericalCoordinates=" + sphericalCoordinates +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
