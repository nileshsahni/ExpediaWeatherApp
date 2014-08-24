package com.expedia.weatherapp.controller;

import com.expedia.weatherapp.common.Constants;
import com.expedia.weatherapp.model.WeatherRequest;
import com.expedia.weatherapp.model.WeatherResponse;
import com.expedia.weatherapp.service.IWeatherAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * WeatherAppController for launching service and getting results.
 * Created by Nilesh Sahni on 08/21/2014.
 */
@Controller
@RequestMapping (value = "/")
public class WeatherAppController {

    public static final Logger logger = LoggerFactory.getLogger(WeatherAppController.class.getName());

    @Autowired
    private IWeatherAppService weatherAppService;

    /**
     * Context to launch landing page for Weather App.
     * @param model
     * @return
     */
    @RequestMapping (value = {"/", Constants.WEATHER_PAGE}, method = RequestMethod.GET)
    public String getHomePage (Model model) {
        logger.info("Get Home Page !!!");
        model.addAttribute(Constants.WEATHER_REQ_ATTR, new WeatherRequest());
        return Constants.WEATHER_PAGE;
    }

    /**
     * Fetch Weather by Zip Code.
     * Valid annotation is used for validation Zip Code.
     * @param weather
     * @param result
     * @param model
     * @return Weather
     */
    @RequestMapping(value = Constants.WEATHER_REQ_MAPPING, method = {RequestMethod.POST})
    public String getWeather(@Valid WeatherRequest weather, BindingResult result, Model model) {
        logger.debug("Start | getWeather Zip Code: " + weather.getZipCode());
        try {
            // Check for Validation errors in the Bind Result.
            if (!result.hasErrors()) {
                WeatherResponse weatherResponse = this.weatherAppService.getWeatherByZipCode(weather);
                if (null != weatherResponse.getResponse().getError()) { // Weather response has errors.
                    result.rejectValue("zipCode", "weather.zipcode.notfound", "Zip Code not found");
                } else {
                    model.addAttribute(Constants.WEATHER_RESP_ATTR, weatherResponse);
                }
            }
        } catch (RuntimeException e) {
            logger.error("Error getting response: ", e);
            result.rejectValue("zipCode", "weather.service.error", "Error getting response from weather service.");
        } catch (Exception e) {
            logger.error("Error getting response: ", e);
            result.rejectValue("zipCode", "weather.service.error", "Error getting response from weather service.");
        }
        logger.debug("End | getWeather");
        return Constants.WEATHER_PAGE;
    }
}