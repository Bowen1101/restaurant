package com.ascending.training.service;


import com.ascending.training.bowen.init.AppInitializer;
import com.ascending.training.bowen.model.Merchant;
import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.service.MerchantService;
import com.ascending.training.bowen.service.RestaurantService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class MerchantServiceTest {
//    private MerchantDaoImpl merchantDaoImpl;
    private Merchant a = new Merchant();
    private String expectedNameOfMerchant = "Test";
    private Restaurant restaurant = new Restaurant();
//    private RestaurantDaoImpl resturantDaoImpl = new RestaurantDaoImpl();

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private RestaurantService restaurantService;

    @Before
    public void init(){
//        merchantService = new MerchantService();
        a.setName(expectedNameOfMerchant);
        a.setFirstName("AAA");
        a.setLastName("BBB");
        a.setEmail("CCC");
        a.setTel("111");
        a.setAddress("DDD");
        restaurant = restaurantService.getRestaurantByName("Young Chow");
        a.setRestaurant(restaurant);
//        a.setResturantId(1);
        merchantService.save(a);
    }

    @Test
    public void saveTest(){
        int expectedNumOfMerchant = 3;
        List<Merchant> merchants = merchantService.getMerchants();
        Assert.assertEquals(expectedNumOfMerchant,merchants.size());
    }

    @Test
    public void updateTest(){
        String expectedName = "update name test";
        a.setName("update name test");
        Assert.assertEquals(expectedName,a.getName());
    }

    @Test
    public void deleteTest(){
        merchantService.delete(expectedNameOfMerchant);
        int expectedNumOfMerchant = 2;
        List<Merchant> merchants = merchantService.getMerchants();
        Assert.assertEquals(expectedNumOfMerchant,merchants.size());
    }

    @Test
    public void getMerchantsTest(){
        int expectedNumOfMerchant = 3;
        List<Merchant> merchants = merchantService.getMerchants();
        Assert.assertEquals(expectedNumOfMerchant,merchants.size());
    }

    @Test
    public void getMerchantByNameTest(){
        Merchant b = merchantService.getMerchantByName(expectedNameOfMerchant);
        Assert.assertEquals(expectedNameOfMerchant,b.getName());
    }

    @After
    public void cleanup(){
        if(merchantService.getMerchantByName(expectedNameOfMerchant)!= null) {
            merchantService.delete(expectedNameOfMerchant);
        }
        merchantService = null;
        restaurantService = null;
    }


}
