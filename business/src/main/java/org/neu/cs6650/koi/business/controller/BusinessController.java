package org.neu.cs6650.koi.business.controller;

import org.neu.cs6650.koi.business.service.BusinessService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO) {
        log.info("Request parameter: {}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    ObjectResponse getStock(@RequestParam Map<String, String> requestParams) {
        String commodityCode = requestParams.get("commodity_code");
        if (commodityCode != null) {
            CommodityDTO commodityDTO = new CommodityDTO();
            commodityDTO.setCommodityCode(commodityCode);
            return businessService.getStock(commodityDTO);
        } else {
            return businessService.getAllStocks();
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    ObjectResponse getOrderByOId(@RequestParam Map<String, String> requestParams) {
        String userId = requestParams.get("user_id");
        String orderNo = requestParams.get("order_no");
        if (userId != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUserId(userId);
            return businessService.getOrderByUId(accountDTO);
        } else {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderNo(orderNo);
            return businessService.getOrderByOId(orderDTO);
        }
    }
}
