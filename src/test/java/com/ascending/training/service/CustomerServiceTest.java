package com.ascending.training.service;


import com.ascending.training.bowen.init.AppInitializer;
import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.service.AreaService;
import com.ascending.training.bowen.service.CustomerService;
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
public class CustomerServiceTest {

//    private CustomerDaoImpl customerDaoImpl;
    private String expectedCustomerName = "Test";
    private Customer a = new Customer();
    private Area area = new Area();
//    private AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AreaService areaService;

    @Before
    public void init(){
//        customerDaoImpl = new CustomerDaoImpl();
        a.setName(expectedCustomerName);
        a.setFirstName("AAA");
        a.setLastName("BBB");
        a.setEmail("CCC");
        a.setTel("111");
        a.setAddress("DDD");
        area = areaService.getAreaByName("Crystal City");
        a.setArea(area);
//        a.setAreaId(1);
        customerService.save(a);
    }

    @After
    public void cleanup(){

        if (customerService.getCustomerByName(expectedCustomerName)!=null){
            customerService.delete(expectedCustomerName);
            System.out.println("This record has been deleted!");
        }

        customerService = null;
        areaService = null;
    }

    @Test
    public void saveTest(){
        List<Customer> customers = customerService.getCustomers();
        int expectedNumOfCustomer = 3;
        Assert.assertEquals(expectedNumOfCustomer,customers.size());
    }

    @Test
    public void updateTest(){
        a.setName("Update Test");
        Assert.assertNotEquals(expectedCustomerName,a.getName());
    }

    @Test
    public void deleteTest(){
        int expectedNumOfCustomer = 2;
        customerService.delete(expectedCustomerName);
        List<Customer> customers = customerService.getCustomers();
        Assert.assertEquals(expectedNumOfCustomer,customers.size());
    }

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerService.getCustomers();
        int expectedNumOfCustoer = 3;
        Assert.assertEquals(expectedNumOfCustoer,customers.size());
    }

    @Test
    public void getCustomerByNameTest(){
        Customer b = customerService.getCustomerByName(expectedCustomerName);
        Assert.assertEquals(expectedCustomerName,b.getName());
    }

}
