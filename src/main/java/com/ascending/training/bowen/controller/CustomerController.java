package com.ascending.training.bowen.controller;

import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private Logger logger;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/{customerName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomerByName(@PathVariable String customerName){
        return customerService.getCustomerByName(customerName);
    }

    @RequestMapping(value = "/{areaName}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createCustomer(@RequestBody Customer customer, @PathVariable String areaName){
        logger.debug(String.format("Area name: %s, customer: %s", areaName, customer.toString()));
        String msg = "The customer was created.";
        boolean isSuccess = customerService.save(customer, areaName);

        if(!isSuccess) msg = "The customer was not created.";

        return msg;
    }

    @RequestMapping(value = "/{areaName}/{customerName}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createCustomer(@RequestBody Customer customer, @PathVariable String areaName, @PathVariable String customerName){
        logger.debug("Customer:" + customerName);
        String msg = "The customer was updated.";
        boolean isSuccess = customerService.update(customer, areaName, customerName);

        if(!isSuccess) msg = "The customer was not updated.";

        return msg;
    }

    @RequestMapping(value = "/{customerName}",method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable String customerName){
        logger.debug("Customer:" + customerName);
        String msg = "The customer was delted.";
        boolean isSuccess = customerService.delete(customerName);

        if(!isSuccess) msg = "The customer was not deleted.";

        return msg;
    }

}
