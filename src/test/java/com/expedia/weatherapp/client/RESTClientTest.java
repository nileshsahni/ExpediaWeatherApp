package com.expedia.weatherapp.client;

import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import org.aspectj.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Mock test for the REST Client.
 * Created by Nilesh Sahni on 08/21/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/test/resources/applicationContext-test.xml")
public class RESTClientTest {

    private static final Integer VALID_ZIP_CODE = 94117;
    private static final Integer INVALID_ZIP_CODE = 11111;

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplete;

    @Autowired
    private RESTClient client;

    @Before
    public void setUp() {
        // Create a mock Server instance for RestTemplate.
        this.mockServer = MockRestServiceServer.createServer(this.restTemplete);
    }
    /**
     * Test Success JSON response from weather service.
     * @throws Exception
     */
    @Test
    public void testGetSuccessJSONResponse() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(RESTClientTest.VALID_ZIP_CODE);

        this.mockServer
            .expect(requestTo(this.getWeatherServiceURL(request.getZipCode())))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(this.getWeatherResponse(request.getZipCode()), MediaType.APPLICATION_JSON));

        WeatherResponse weatherResponse = this.client.getJSONResponse(request);
        assertNull(weatherResponse.getResponse().getError());
        this.mockServer.verify();
    }

    /**
     * Test Error JSON response from Weather Service.
     * @throws Exception
     */
    @Test
    public void testGetErrorJSONResponse() throws Exception {
        WeatherRequest request = new WeatherRequest();
        request.setZipCode(RESTClientTest.INVALID_ZIP_CODE);

        this.mockServer
                .expect(requestTo(this.getWeatherServiceURL(request.getZipCode())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(this.getWeatherResponse(request.getZipCode()), MediaType.APPLICATION_JSON));

        WeatherResponse weatherResponse = this.client.getJSONResponse(request);
        assertNotNull(weatherResponse.getResponse().getError());
        this.mockServer.verify();
    }

    /**
     * Helper method to generate expected Weather service URL.
     * @param zipCode
     * @return
     */
    private String getWeatherServiceURL (Integer zipCode) {
        return this.client.getServiceURI()
            .replace("{apiKey}", this.client.getApiKey())
            .replace("{zip}", String.valueOf(zipCode));
    }

    /**
     * Helper method to read weather JSON stubs as string.
     * @param zipCode
     * @return
     * @throws IOException
     */
    private String getWeatherResponse (Integer zipCode) throws IOException {
        String filePath = this.getClass().getClassLoader().getResource("data/" + String.valueOf(zipCode) + ".json").getPath();
        return FileUtil.readAsString(new File(filePath));
    }
}