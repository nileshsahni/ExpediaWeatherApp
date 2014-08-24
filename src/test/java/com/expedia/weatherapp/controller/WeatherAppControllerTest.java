package com.expedia.weatherapp.controller;

import com.expedia.weatherapp.common.Constants;
import com.expedia.weatherapp.model.Error;
import com.expedia.weatherapp.model.Response;
import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import com.expedia.weatherapp.service.IWeatherAppService;
import com.expedia.weatherapp.service.WeatherAppService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * JUnit tests using Mock for the Weather Controller.
 * Created by Nilesh Sahni on 08/22/2014.
 */
@RunWith (MockitoJUnitRunner.class)
public class WeatherAppControllerTest {
    private static final Integer ZIP_CODE_VALID = 12345;
    private static final String ZIP_CODE_INVALID = "abc";

    private MockMvc mockMvc;
    @InjectMocks
    private WeatherAppController weatherAppController;

    @Mock
    private WeatherAppService weatherAppServiceMock;

    @Before
    public void setup() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.weatherAppController)
                        .setValidator(new LocalValidatorFactoryBean()).build();
    }
    /**
     * Success case to access Weather App Page.
     * @throws Exception
     */
    @Test
    public void testGetWeatherHomePageSuccess() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.WEATHER_PAGE))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists(Constants.WEATHER_REQ_ATTR));
    }
    /**
     * Failure case to access Weather App Page.
     * @throws Exception
     */
    @Test
    public void testGetHomePageFail() throws Exception {
        this.mockMvc.perform(post("/"))
                .andExpect(status().isMethodNotAllowed());
    }
    /**
     * Success case when zip code and request is valid.
     * @throws Exception
     */
    @Test
    public void testGetWeatherSuccess() throws Exception {
        when(this.weatherAppServiceMock.getWeatherByZipCode(any(WeatherRequest.class))).thenReturn(this.getValidWeatherResponse());
        this.mockMvc.perform(post(Constants.WEATHER_REQ_MAPPING)
                .param(Constants.ZIP_CODE_PARAM, String.valueOf(ZIP_CODE_VALID)))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name(Constants.WEATHER_PAGE));
        verify(this.weatherAppServiceMock, times(1)).getWeatherByZipCode(any(WeatherRequest.class));
    }
    /**
     * Failure case when GET method is used instead of POST, resulting in HTTP Error 405.
     * @throws Exception
     */
    @Test
    public void testGetWeatherFail() throws Exception {
        this.mockMvc.perform(get(Constants.WEATHER_REQ_MAPPING)
                .param(Constants.ZIP_CODE_PARAM, String.valueOf(ZIP_CODE_VALID)))
                .andExpect(status().isMethodNotAllowed());
        verifyZeroInteractions(this.weatherAppServiceMock);
    }
    /**
     * Invalid Zip Code with character, resulting in validation error.
     * @throws Exception
     */
    @Test
    public void testGetWeatherErrorInValidZipCode() throws Exception {
        this.mockMvc.perform(post(Constants.WEATHER_REQ_MAPPING)
                .param(Constants.ZIP_CODE_PARAM, WeatherAppControllerTest.ZIP_CODE_INVALID))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
        verifyZeroInteractions(this.weatherAppServiceMock);
    }
    /**
     * Zip Code is valid but error is generated from the Weather service.
     * @throws Exception
     */
    @Test
    public void testGetWeatherErrorResponse() throws Exception {
        when(this.weatherAppServiceMock.getWeatherByZipCode(any(WeatherRequest.class))).thenReturn(this.getInValidWeatherResponse());
        this.mockMvc.perform(post(Constants.WEATHER_REQ_MAPPING)
                .param(Constants.ZIP_CODE_PARAM, String.valueOf(WeatherAppControllerTest.ZIP_CODE_VALID)))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist(Constants.WEATHER_RESP_ATTR))
                .andExpect(model().hasErrors());
        verify(this.weatherAppServiceMock, times(1)).getWeatherByZipCode(any(WeatherRequest.class));
    }
    /**
     * Helper method to Invalid response.
     * @return
     */
    private WeatherResponse getInValidWeatherResponse () {
        WeatherResponse response = new WeatherResponse();
        response.setResponse(new Response());
        response.getResponse().setError(new Error());
        return response;
    }

    /**
     * Helper method for valid response.
     * @return
     */
    private WeatherResponse getValidWeatherResponse () {
        WeatherResponse response = new WeatherResponse();
        response.setResponse(new Response());
        return response;
    }
}