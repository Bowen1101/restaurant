package com.ascending.training.repository;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.repository.AreaDaoImpl;
import com.ascending.training.bowen.repository.CustomerDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerDaoImplTest {
    private CustomerDaoImpl customerDaoImpl;
    private String expectedCustomerName = "Test";
    private Customer a = new Customer();
    private Area area = new Area();
    private AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

    @Before
    public void init(){
        customerDaoImpl = new CustomerDaoImpl();
        a.setName(expectedCustomerName);
        a.setFirstName("AAA");
        a.setLastName("BBB");
        a.setEmail("CCC");
        a.setTel("111");
        a.setAddress("DDD");
        area = areaDaoImpl.getAreaByName("Crystal City");
        a.setArea(area);
//        a.setAreaId(1);
        customerDaoImpl.save(a);
    }

    @After
    public void cleanup(){

        if (customerDaoImpl.getCustomerByName(expectedCustomerName)!=null){
            customerDaoImpl.delete(expectedCustomerName);
            System.out.println("This record has been deleted!");
        }

        customerDaoImpl = null;
        areaDaoImpl = null;
    }

    @Test
    public void saveTest(){
        List<Customer> customers = customerDaoImpl.getCustomers();
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
        customerDaoImpl.delete(expectedCustomerName);
        List<Customer> customers = customerDaoImpl.getCustomers();
        Assert.assertEquals(expectedNumOfCustomer,customers.size());
    }

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerDaoImpl.getCustomers();
        int expectedNumOfCustoer = 3;
        Assert.assertEquals(expectedNumOfCustoer,customers.size());
    }

    @Test
    public void getCustomerByNameTest(){
        Customer b = customerDaoImpl.getCustomerByName(expectedCustomerName);
        Assert.assertEquals(expectedCustomerName,b.getName());
    }
}
