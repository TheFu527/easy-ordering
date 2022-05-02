package org.neu.cs6650.koi.business.controller;

import org.neu.cs6650.koi.business.service.BusinessService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/dubbo")
@Slf4j
public class BusinessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private BusinessService businessService;

    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO) {
        LOGGER.info("Request parameter: {}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    //post api for login.
    //return user row if user exist, otherwise create new user.
    @PostMapping("/account")
    ObjectResponse handleLogin(String name, String password) {
        LOGGER.info("Request parameter: {}", name, password);
        return businessService.handleLogin(name, password);
    }

    //post api for register(create new user).
    //return all info including user id.
    @PostMapping("/newAccount")
    ObjectResponse handleRegister(String name, String password) {
        LOGGER.info("Request parameter: {}", name, password);
        return businessService.handleRegister(name, password);
    }

    //post api for getting existing user info.
    @GetMapping("/account")
    ObjectResponse handleGetAccount(String user_name, String password) {
        LOGGER.info("Request parameter: {}", user_name, password);
        return businessService.handleGetAccount(user_name, password);
    }
}
