package com.expedia.weatherapp.client;

import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration Test Case for the REST Client, to test actual integration with Weather Service.
 * Not meant to be executed during build. Only for Dev integration purpose.
 * Created by Nilesh Sahni on 08/21/2014.
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations={"file:src/test/resources/applicationContext-test.xml"})
public class TestRESTClient {

    private static final Integer VALID_ZIP_CODE = 94117;
    private static final Integer INVALID_ZIP_CODE = 11111;

    @Autowired
    private IRESTClient client;

    /**
     * Test Valid JSON Response from Weather Service.
     * @throws Exception
     */
    //@Test
    public void testGetValidJSONResponse() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(TestRESTClient.VALID_ZIP_CODE);
        WeatherResponse weatherResponse = this.client.getJSONResponse(request);
        assertNotNull(weatherResponse);
        assertNull(weatherResponse.getResponse().getError());
    }

    /**
     * Test Invalid JSON Response from Weather Service.
     * @throws Exception
     */
    //@Test
    public void testGetInValidJSONResponse() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(TestRESTClient.INVALID_ZIP_CODE);
        WeatherResponse weatherResponse = this.client.getJSONResponse(request);
        assertNotNull(weatherResponse);
        assertNotNull(weatherResponse.getResponse().getError());
    }
}
