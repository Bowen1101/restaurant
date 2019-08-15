package com.ascending.training.jdbc;

import com.ascending.training.bowen.jdbc.CustomerDao;
import com.ascending.training.bowen.model.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {


    @Test
    public void getCustomersTest() {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getCustomer();
        int expectedNumOfCustomer = 2;

        for (Customer customer : customers){
            System.out.println(customer.getName());
        }

        Assert.assertEquals(expectedNumOfCustomer,customers.size());
    }
}
