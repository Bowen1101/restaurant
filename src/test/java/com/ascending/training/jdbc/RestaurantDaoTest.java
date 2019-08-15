package com.ascending.training.jdbc;

import com.ascending.training.bowen.jdbc.RestaurantDao;
import com.ascending.training.bowen.model.Restaurant;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RestaurantDaoTest {


    @Test
    public void getRestaurantTest() {
        RestaurantDao restaurantDao = new RestaurantDao();
        List<Restaurant> restaurants = restaurantDao.getRestaurant();
        int expectedNumOfRestaurant = 2;

        for(Restaurant restaurant : restaurants){
            System.out.println(restaurant.getName());
        }

        Assert.assertEquals(expectedNumOfRestaurant, restaurants.size());
    }
}
