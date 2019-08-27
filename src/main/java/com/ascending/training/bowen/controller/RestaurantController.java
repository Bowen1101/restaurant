package com.ascending.training.bowen.controller;

import com.ascending.training.bowen.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/restaurants"})
public class RestaurantController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestaurantService restaurantService;




}
