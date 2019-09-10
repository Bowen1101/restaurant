package com.ascending.training.bowen.controller;


import com.ascending.training.bowen.model.User;
import com.ascending.training.bowen.service.AreaService;
import com.ascending.training.bowen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/users"})
public class UserController {
    @Autowired
    private Logger logger;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = "application/json")
    public User user(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
