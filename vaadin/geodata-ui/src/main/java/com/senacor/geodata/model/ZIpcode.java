package com.senacor.geodata.model;

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

}
