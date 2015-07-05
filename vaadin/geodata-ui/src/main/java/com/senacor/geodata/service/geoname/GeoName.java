package com.senacor.geodata.service.geoname;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dschmitz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoName {
    private String name;
    private double lat;
    private double lng;
    private String countrycode;
    private String geonameId;
    private String wikipedia;

    public String getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(String geonameId) {
        this.geonameId = geonameId;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }
    //{"fcodeName":"capital of a political entity"
    // ,"toponymName":"Mexico City",
    // "countrycode":"MX","fcl":"P","fclName":"city, village,...",
    // "name":"Mexiko-Stadt",
    // "wikipedia":"en.wikipedia.org/wiki/Mexico_City",
    // "lng":-99.12766456604,"fcode":"PPLC",
    // "geonameId":3530597,
    // "lat":19.428472427036,"population":12294193}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countryCode) {
        this.countrycode = countryCode;
    }

    @Override
    public String toString() {
        return "GeoName{" +
                "name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", countryCode='" + countrycode + '\'' +
                '}';
    }
}
