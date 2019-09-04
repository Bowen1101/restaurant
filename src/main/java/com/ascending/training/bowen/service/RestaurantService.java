package com.ascending.training.bowen.service;

import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.repository.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    public boolean save(Restaurant restaurant){
        return restaurantDao.save(restaurant);
    }

    public boolean save(Restaurant restaurant, String areaname) {
        return restaurantDao.save(restaurant, areaname);
    }

    public boolean update(Restaurant restaurant, String areaName, String name) {
        return restaurantDao.update(restaurant, areaName, name);
    }

    public boolean update(Restaurant restaurant){
        return restaurantDao.update(restaurant);
    }

    public boolean delete(String restaurantName){
        return restaurantDao.delete(restaurantName);
    }

    public List<Restaurant> getRestaurants(){
        return restaurantDao.getRestaurants();
    }

    public Restaurant getRestaurantByName(String restaurantName){
        return restaurantDao.getRestaurantByName(restaurantName);
    }

}
