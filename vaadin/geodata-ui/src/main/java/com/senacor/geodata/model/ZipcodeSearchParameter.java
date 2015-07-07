package com.senacor.geodata.model;

/**
 * Created by mblume on 07.07.15.
 */
public class ZipcodeSearchParameter {
    private final String zipcode;
    private final String countryCode;

    private final int diameter;

    public ZipcodeSearchParameter(String zipcode, String countryCode, int diameter) {
        this.zipcode = zipcode;
        this.countryCode = countryCode;
        this.diameter = diameter;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return "ZipcodeSearchParameter{" +
            "zipcode='" + zipcode + '\'' +
            ", countryCode='" + countryCode + '\'' +
            ", diameter=" + diameter +
            '}';
    }
}
