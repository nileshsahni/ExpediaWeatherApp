package com.expedia.weatherapp.client;

import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@Component
public class RESTClient implements IRESTClient {
    public static final Logger logger = LoggerFactory.getLogger(RESTClient.class.getName());

    @Autowired
    private RestOperations restTemplete;

    @Value("${weather.service.json.url}")
    private String serviceURI;

    @Value("${weather.service.key}")
    private String apiKey;

    @Override
    public WeatherResponse getJSONResponse(WeatherRequest request) {
        logger.info("Start | Enter getJSONResponse");
        ResponseEntity<WeatherResponse> response = this.restTemplete.exchange(this.serviceURI, HttpMethod.GET, null, WeatherResponse.class, this.apiKey, request.getZipCode());
        WeatherResponse weatherResponse = response.getBody();
        logger.info("End | Enter getJSONResponse");
        return weatherResponse;
    }

    public String getServiceURI() {
        return serviceURI;
    }

    public void setServiceURI(String serviceURI) {
        this.serviceURI = serviceURI;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}