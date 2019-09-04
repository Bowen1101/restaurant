package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean save(Customer customer);
    boolean save(Customer customer, String areaName);
    boolean update(Customer customer);
    boolean update(Customer customer, String areaName, String customerName);
    boolean delete(String customerName);
    List<Customer> getCustomers();
    Customer getCustomerByName(String customerName);
}
