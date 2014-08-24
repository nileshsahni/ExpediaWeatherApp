package com.expedia.weatherapp.service;

import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@Service
public interface IWeatherAppService {
    public WeatherResponse getWeatherByZipCode(WeatherRequest weatherRequest);
}
