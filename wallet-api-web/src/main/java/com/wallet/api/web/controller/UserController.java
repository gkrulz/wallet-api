package com.wallet.api.web.controller;

import com.wallet.api.beans.User;
import com.wallet.api.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        logger.info("Creating new user : {}", user.getId());

        return userService.createUser(user);
    }
}
