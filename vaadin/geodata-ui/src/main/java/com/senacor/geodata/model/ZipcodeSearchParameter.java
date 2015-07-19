package com.senacor.geodata.model;

/**
 * Created by mblume on 07.07.15.
 */
public class ZipcodeSearchParameter {
    private String zipcode;
    private String countryCode;

    private int diameter;

    public ZipcodeSearchParameter(String zipcode, String countryCode, int diameter) {
        this.zipcode = zipcode;
        this.countryCode = countryCode;
        this.diameter = diameter;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
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
