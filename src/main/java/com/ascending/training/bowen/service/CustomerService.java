package com.ascending.training.bowen.service;

import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public boolean save(Customer customer){
        return customerDao.save(customer);
    }

    public boolean save(Customer customer, String areaName){
        return customerDao.save(customer,areaName);
    }

    public boolean update(Customer customer){
        return customerDao.update(customer);
    }

    public boolean update(Customer customer, String areaName, String customerName){
        return customerDao.update(customer, areaName, customerName);
    }

    public boolean delete(String customerName){
        return customerDao.delete(customerName);
    }

    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }

    public Customer getCustomerByName(String customerName){
        return customerDao.getCustomerByName(customerName);
    }
}
