package org.neu.cs6650.koi.business.controller;

import org.neu.cs6650.koi.business.service.BusinessService;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
