package com.senacor.geodata.model;

import com.senacor.geodata.service.geoname.GeoZipcode;

/**
 * Created by mblume on 07.07.15.
 */
public class Zipcode {

    private final String code;

    private final String city;

    public Zipcode(String code, String city) {
        this.code = code;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public static Zipcode fromGeoZipcode(GeoZipcode geoZipcode) {
        return new Zipcode(geoZipcode.getPostalCode(), geoZipcode.getPlaceName());
    }
}
