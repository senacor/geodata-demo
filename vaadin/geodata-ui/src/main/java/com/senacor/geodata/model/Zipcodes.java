package com.senacor.geodata.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mblume on 07.07.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zipcodes {
    private List<Zipcode> zipcodes;

    public List<Zipcode> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(List<Zipcode> zipcodes) {
        this.zipcodes = zipcodes;
    }
}
