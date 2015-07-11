package com.senacor.geodata.service.geoname;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mblume on 08.07.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoZipcodes {
    private List<GeoZipcode> postalCodes = new LinkedList<>();

    public List<GeoZipcode> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(List<GeoZipcode> postalCodes) {
        this.postalCodes = postalCodes;
    }

    @Override
    public String toString() {
        return "GeoZipcode{" +
            "geoZipcodes=" + postalCodes +
            '}';
    }

}
