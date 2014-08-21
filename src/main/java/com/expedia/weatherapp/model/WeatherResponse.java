package com.expedia.weatherapp.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class WeatherResponse {
    private Response response;
    private CurrentObservation current_observation;

    public CurrentObservation getCurrent_observation() {
        return current_observation;
    }

    public void setCurrent_observation(CurrentObservation current_observation) {
        this.current_observation = current_observation;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
