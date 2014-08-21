package com.expedia.weatherapp.controller;

import com.expedia.weatherapp.common.Constants;
import com.expedia.weatherapp.model.WeatherResponse;
import com.expedia.weatherapp.service.IWeatherAppService;
import com.expedia.weatherapp.service.WeatherAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nilesh Sahni on 08/21/2014.
 */
@Controller
@RequestMapping (value = "/")
public class WeatherAppController {

    public static final Logger logger = LoggerFactory.getLogger(WeatherAppController.class.getName());

    @Autowired
    private IWeatherAppService weatherAppService;

    @RequestMapping (value = {"/", "home"}, method = RequestMethod.GET)
    public String getHome () {
        logger.info("Get Home Page !!!");
        return "home";
    }

    /**
     * Call for fetching the Zip code
     * Valid annotations can also be used for validation of empty value check & length of the String
     *
     * @param zipCode Valid US Zip code
     * @return Weather
     */
    @RequestMapping(value = "/getWeather", method = {RequestMethod.GET}, produces = "application/json")
    public @ResponseBody
    WeatherResponse getWeather(@RequestParam(Constants.ZIP_CODE_PARAM) String zipCode) {
        logger.debug("Start | getWeather Zip Code: " + zipCode);
        WeatherResponse response = this.weatherAppService.getWeatherByZipCode(zipCode);
        logger.debug("End | getWeather Zip Code: " + response);
        return response;
    }
}