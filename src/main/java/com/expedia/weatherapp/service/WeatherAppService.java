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
    public WeatherResponse getWeatherByZipCode(String zipCode) {
        logger.debug("Start | getWeatherByZipCode Zip Code: " + zipCode);
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(zipCode);
        return this.restClient.getJSONResponse(request);
    }
}
