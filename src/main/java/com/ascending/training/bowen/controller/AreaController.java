package com.ascending.training.bowen.controller;


import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/areas"})
public class AreaController {
    @Autowired
    private Logger logger;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Area> getAreas() {
        return areaService.getAreas();
    }

    @RequestMapping(value = "/{areaName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Area getArea(@PathVariable String areaName) {
        return areaService.getAreaByName(areaName);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createArea(@RequestBody Area area) {
        logger.debug("Area:" + area.toString());
        String msg = "The area was created.";
        boolean isSuccess = areaService.save(area);

        if (!isSuccess) msg = "The area was not created.";

        return msg;
    }

    @RequestMapping(value = "/{areaName}", method = RequestMethod.DELETE)
    public String deleteArea(@PathVariable String areaName) {
        logger.debug("Area:" + areaName);
        String msg = "The area was deleted.";
        boolean isSuccess = areaService.delete(areaName);

        if (!isSuccess) msg = "The area was not deleted.";

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateArea(@RequestBody Area area){
        logger.debug("Area:" + area.toString());
        String msg = "The area was updated.";
        boolean isSuccess = areaService.update(area);

        if (!isSuccess) msg = "The area was not updated.";

        return msg;
    }



//    @RequestMapping(value = "/{areaName}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public String creatRestaurant(@RequestBody Restaurant restaurant, @PathVariable String areaName) {
//        logger.debug(String.format("Area name: %s, restaurant: %s", areaName, restaurant.toString()));
//        String msg = "The restaurant was created.";
//        boolean isSuccess = restaurantService.save(restaurant);
//
//        if(!isSuccess) msg = "The restaurant was not created.";
//
//        return msg;
//    }

}
