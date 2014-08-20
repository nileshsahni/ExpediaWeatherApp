package com.expedia.weatherapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping (value = "/")
public class HomeController {

    public static final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());

    @RequestMapping (value = {"/", "home"}, method = RequestMethod.GET)
    public String getHome () {
        logger.info("Get Home Page !!!");
        return "home";
    }
}