package com.ascending.training.bowen.controller;

import com.ascending.training.bowen.model.Merchant;
import com.ascending.training.bowen.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = {"/merchants"})
public class MerchantController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Merchant> getMerchants(){
        return merchantService.getMerchants();
    }

    @RequestMapping(value = "/{merchantName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Merchant getMerchantByName(@PathVariable(name = "merchantName") String name){
        return merchantService.getMerchantByName(name);
    }

    @RequestMapping(value = "/{restaurantName}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createMerchant(@RequestBody Merchant merchant, @PathVariable(name = "restaurantName") String name){
        logger.debug(String.format("Restaurant name: %s, merchant: %s", name, merchant.toString()));
        String msg = "The merchant was created.";
        boolean isSuccess = merchantService.save(merchant, name);

        if (!isSuccess) msg = "The merchant was not created.";

        return msg;
    }

    @RequestMapping(value = "/{restaurantName}/{merchantName}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateMerchant(@RequestBody Merchant merchant, @PathVariable String restaurantName, @PathVariable String merchantName){
        logger.debug(String.format("Merchant"+ merchant.toString()));
        String msg = "The merchant was updated.";
        boolean isSuccess = merchantService.update(merchant, restaurantName, merchantName);

        if(!isSuccess) msg = "The merchant was not updated";

        return msg;
    }

    @RequestMapping(value = "/{merchantName}", method = RequestMethod.DELETE)
    public String deleteMerchant(@PathVariable String merchantName){
        logger.debug("Merchant"+ merchantName);
        String msg = "The merchant was deleted.";
        boolean isSuccess = merchantService.delete(merchantName);

        if(!isSuccess) msg = "The merchant was not deleted.";

        return msg;
    }
}
