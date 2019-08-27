package com.ascending.training.repository;

import com.ascending.training.bowen.model.Merchant;
import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.repository.MerchantDaoImpl;
import com.ascending.training.bowen.repository.RestaurantDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MerchantDaoImplTest {
    private MerchantDaoImpl merchantDaoImpl;
    private Merchant a = new Merchant();
    private String expectedNameOfMerchant = "Test";
    private Restaurant restaurant = new Restaurant();
    private RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl();

    @Before
    public void init(){
        merchantDaoImpl = new MerchantDaoImpl();
        a.setName(expectedNameOfMerchant);
        a.setFirstName("AAA");
        a.setLastName("BBB");
        a.setEmail("CCC");
        a.setTel("111");
        a.setAddress("DDD");
        restaurant = restaurantDaoImpl.getRestaurantByName("Young Chow");
        a.setRestaurant(restaurant);
//        a.setResturantId(1);
        merchantDaoImpl.save(a);
    }

    @Test
    public void saveTest(){
        int expectedNumOfMerchant = 3;
        List<Merchant> merchants = merchantDaoImpl.getMerchants();
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
        merchantDaoImpl.delete(expectedNameOfMerchant);
        int expectedNumOfMerchant = 2;
        List<Merchant> merchants = merchantDaoImpl.getMerchants();
        Assert.assertEquals(expectedNumOfMerchant,merchants.size());
    }

    @Test
    public void getMerchantsTest(){
        int expectedNumOfMerchant = 3;
        List<Merchant> merchants = merchantDaoImpl.getMerchants();
        Assert.assertEquals(expectedNumOfMerchant,merchants.size());
    }

    @Test
    public void getMerchantByNameTest(){
        Merchant b = merchantDaoImpl.getMerchantByName(expectedNameOfMerchant);
        Assert.assertEquals(expectedNameOfMerchant,b.getName());
    }

    @After
    public void cleanup(){
        if(merchantDaoImpl.getMerchantByName(expectedNameOfMerchant)!= null) {
            merchantDaoImpl.delete(expectedNameOfMerchant);
        }
        merchantDaoImpl = null;
        restaurantDaoImpl = null;
    }
}
