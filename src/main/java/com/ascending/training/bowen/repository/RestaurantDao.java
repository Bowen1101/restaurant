package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    boolean save(Restaurant restaurant);
    boolean save(Restaurant restaurant, String areaname);
    boolean update(Restaurant restaurant);
    boolean update(Restaurant restaurant, String areaname, String name);
    boolean delete(String restaurantName);
    List<Restaurant> getRestaurants();
    Restaurant getRestaurantByName(String restaurantName);
}
