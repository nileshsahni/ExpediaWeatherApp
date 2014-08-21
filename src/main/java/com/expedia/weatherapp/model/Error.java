package com.expedia.weatherapp.model;

/**
 * Created by Nilesh Sahni on 08/22/2014.
 */
public class Error {
    private String type;
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}