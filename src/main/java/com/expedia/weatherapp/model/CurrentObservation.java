package com.expedia.weatherapp.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown=true)
public class CurrentObservation {
    private ObservationLocation observation_location;
    private String temp_c;

    public ObservationLocation getObservation_location() {
        return observation_location;
    }

    public void setObservation_location(ObservationLocation observation_location) {
        this.observation_location = observation_location;
    }

    private String temp_f;

    public String getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(String temp_f) {
        this.temp_f = temp_f;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }
}