package com.expedia.weatherapp.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
public class WeatherRequest {

    @Digits (integer = 5, fraction = 0)
    @Range (min = 10000, max = 99999)
    @NotNull
    private Integer zipCode;

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
