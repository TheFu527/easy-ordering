package org.neu.cs6650.koi.business.controller;

import org.neu.cs6650.koi.business.service.BusinessService;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/dubbo")
@Slf4j
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO) {
        log.info("Request parameter: {}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    @PostMapping("/getStock")
    ObjectResponse getStock(@RequestBody CommodityDTO commodityDTO) {
        log.info("Request parameter: {}", commodityDTO.toString());
        return businessService.getStock(commodityDTO);
    }

    @PostMapping("/getAllStocks")
    ObjectResponse getAllStock() {
        log.info("Request All Stocks");
        return businessService.getAllStocks();
    }

    @PostMapping("/getOrderById")
    ObjectResponse getOrderById() {
        log.info("Request All Stocks");
        return businessService.getAllStocks();
    }
}
