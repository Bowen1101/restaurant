package com.ascending.training.jdbc;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AreaDaoTest.class,
        RestaurantDaoTest.class,
        CustomerDaoTest.class,
        MerchantDaoTest.class
})

public class TestAll {
}
