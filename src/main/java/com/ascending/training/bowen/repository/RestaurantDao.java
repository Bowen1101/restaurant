package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    boolean save(Restaurant restaurant);
    boolean update(Restaurant restaurant);
    boolean delete(String restaurantName);
    List<Restaurant> getRestaurants();
    Restaurant getRestaurantByName(String restaurantName);
}
