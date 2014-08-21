package com.expedia.weatherapp.client;

import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import org.springframework.stereotype.Component;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@Component
public interface IRESTClient {
    public WeatherResponse getJSONResponse (WeatherRequest request);
}
