package com.expedia.weatherapp.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String version;
    private String termsofService;
    private Error error;

    public String getVersion() {
        return version;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }
}