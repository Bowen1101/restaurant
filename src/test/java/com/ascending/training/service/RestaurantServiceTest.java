package com.ascending.training.service;

import com.ascending.training.bowen.init.AppInitializer;
import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.service.AreaService;
import com.ascending.training.bowen.service.RestaurantService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class RestaurantServiceTest {

//    private RestaurantDaoImpl restauantDaoImpl;
    private Restaurant a = new Restaurant();
    private String expectedRestaurantName = "Test";
    private Area area = new Area();
//    private AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private AreaService areaService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){

//        restaurantService = new RestaurantService();
        a.setName(expectedRestaurantName);
        a.setCategory("Test");
        a.setAddress("Test");
        a.setTel("Test");
        a.setStarrated(5);
        area = areaService.getAreaByName("Crystal City");
        a.setArea(area);
//        a.setAreaId(2);
        restaurantService.save(a);
    }

    @Test
    public void saveTest(){
        int expectedNumOfRestaurant = 3;
        List<Restaurant> restaurants = restaurantService.getRestaurants();
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
        restaurantService.delete(expectedRestaurantName);
        int expectedNumOfRestaurant = 2;
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Assert.assertEquals(expectedNumOfRestaurant, restaurants.size());
    }

    @Test
    public void getRestaurantTest(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        int expectedNumOfRestaurant = 3;
        Assert.assertEquals(expectedNumOfRestaurant, restaurants.size());
    }

    @Test
    public void getRestaurantByName(){
        Restaurant b = restaurantService.getRestaurantByName(expectedRestaurantName);
        Assert.assertEquals(expectedRestaurantName,b.getName());
    }

    @After
    public void cleanup() {
        if (restaurantService.getRestaurantByName(expectedRestaurantName)!=null){
            restaurantService.delete(expectedRestaurantName);
        }

        restaurantService = null;
        areaService = null;
    }
}
