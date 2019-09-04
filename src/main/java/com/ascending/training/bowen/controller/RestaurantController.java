package com.ascending.training.bowen.controller;

import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = {"/restaurants"})
public class RestaurantController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @RequestMapping(value = "/{restaurantName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Restaurant getRestaurantByName(@PathVariable(name = "restaurantName") String name){
        return restaurantService.getRestaurantByName(name);
    }


//    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public String createRestaurant(@RequestBody Restaurant restaurant){
//        logger.debug("Restaurant:" + restaurant.toString());
//        String msg = "The restaurant was created.";
//        boolean isSuccess = restaurantService.save(restaurant);
//
//        if(!isSuccess) msg="The restaurant was not created";
//
//        return msg;
//    }

    @RequestMapping(value = "/{areaName}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createRestaurant(@RequestBody Restaurant restaurant, @PathVariable String areaName) {
        logger.debug(String.format("Area name: %s, restaurant: %s", areaName, restaurant.toString()));
        String msg = "The restaurant was created.";
        boolean isSuccess = restaurantService.save(restaurant, areaName);

        if (!isSuccess) msg = "The restaurant was not created.";

        return msg;
    }

    @RequestMapping(value = "/{areaName}/{restaurantName}",method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable String areaName, @PathVariable(name = "restaurantName") String name){
        logger.debug("Restaurant" + restaurant.toString());
        String msg = "The restaurant was updated.";
        boolean isSuccess = restaurantService.update(restaurant,areaName,name);

        if (!isSuccess) msg = "The restaurant was not updated.";

        return msg;
    }

    @RequestMapping(value = "/{restaurantName}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable(name = "restaurantName") String name){
        logger.debug("Restaurant:" + name);
        String msg = "The restaurant was deleted.";
        boolean isSuccess = restaurantService.delete(name);

        if (!isSuccess) msg = "The area was not deleted.";

        return msg;
    }
}
