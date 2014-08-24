package com.expedia.weatherapp.service;

import com.expedia.weatherapp.client.IRESTClient;
import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@Service
public class WeatherAppService implements IWeatherAppService {
    public static final Logger logger = LoggerFactory.getLogger(WeatherAppService.class.getName());

    @Autowired
    private IRESTClient restClient;

    @Override
    public WeatherResponse getWeatherByZipCode(WeatherRequest weatherRequest) {
        logger.info("getWeatherByZipCode Zip Code: " + weatherRequest.getZipCode());
        return this.restClient.getJSONResponse(weatherRequest);
    }
}