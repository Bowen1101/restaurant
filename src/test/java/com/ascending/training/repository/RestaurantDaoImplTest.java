package com.ascending.training.repository;


import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.repository.AreaDaoImpl;
import com.ascending.training.bowen.repository.RestaurantDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RestaurantDaoImplTest {
    private RestaurantDaoImpl restaurantDaoImpl;
    private Restaurant a = new Restaurant();
    private String expectedRestaurantName = "Test";
    private Area area = new Area();
    private AreaDaoImpl areaDaoImpl = new AreaDaoImpl();


    @Before
    public void init(){

        restaurantDaoImpl = new RestaurantDaoImpl();
        a.setName(expectedRestaurantName);
        a.setCategory("Test");
        a.setAddress("Test");
        a.setTel("Test");
        a.setStarrated(5);
        area = areaDaoImpl.getAreaByName("Crystal City");
        a.setArea(area);
//        a.setAreaId(2);
        restaurantDaoImpl.save(a);
    }

    @Test
    public void saveTest(){
        int expectedNumOfRestaurant = 3;
        List<Restaurant> restaurants = restaurantDaoImpl.getRestaurants();
        Assert.assertEquals(expectedNumOfRestaurant, restaurants.size());
    }

    @Test
    public void updateTest(){
        a.setName(expectedRestaurantName);
        a.setAddress("Update Test");
        Assert.assertEquals(a.getAddress(),"Update Test");
    }

    @Test
    public void deleteTest(){
        restaurantDaoImpl.delete(expectedRestaurantName);
        int expectedNumOfRestaurant = 2;
        List<Restaurant> restaurants = restaurantDaoImpl.getRestaurants();
        Assert.assertEquals(expectedNumOfRestaurant, restaurants.size());
    }

    @Test
    public void getRestaurantTest(){
        List<Restaurant> restaurants = restaurantDaoImpl.getRestaurants();
        int expectedNumOfRestaurant = 3;
        Assert.assertEquals(expectedNumOfRestaurant, restaurants.size());
    }

    @Test
    public void getRestaurantByName(){
        Restaurant b = restaurantDaoImpl.getRestaurantByName(expectedRestaurantName);
        Assert.assertEquals(expectedRestaurantName,b.getName());
    }

    @After
    public void cleanup() {
        if (restaurantDaoImpl.getRestaurantByName(expectedRestaurantName)!=null){
            restaurantDaoImpl.delete(expectedRestaurantName);
        }

        restaurantDaoImpl = null;
        areaDaoImpl = null;
    }


}
