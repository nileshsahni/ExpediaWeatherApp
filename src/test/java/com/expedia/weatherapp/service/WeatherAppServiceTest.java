package com.expedia.weatherapp.service;

import com.expedia.weatherapp.client.IRESTClient;
import com.expedia.weatherapp.model.WeatherRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Nilesh Sahni on 08/23/2014.
 */
@RunWith (MockitoJUnitRunner.class)
public class WeatherAppServiceTest {
    private static final Integer ZIP_CODE_VALID = 12345;

    @InjectMocks
    private WeatherAppService weatherAppService;

    @Mock
    private IRESTClient restClient;

    /**
     * Weather App Service Test.
     * @throws Exception
     */
    @Test
    public void testGetWeatherByZipCode() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(WeatherAppServiceTest.ZIP_CODE_VALID);
        this.weatherAppService.getWeatherByZipCode(request);
        verify(this.restClient, times(1)).getJSONResponse(any(WeatherRequest.class));
    }
}