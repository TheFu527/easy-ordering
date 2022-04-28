package org.neu.cs6650.koi.business.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.neu.cs6650.koi.business.service.BusinessService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    ObjectResponse getStock(@RequestParam(name = "commodityCode") String commodityCode) {
        log.info("Request Stocks commodityCode = " + commodityCode);
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(commodityCode);
        return businessService.getStock(commodityDTO);
    }

    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    ObjectResponse getAllStock() {
        log.info("Request All Stocks");
        return businessService.getAllStocks();
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    ObjectResponse getOrderByOId(@RequestParam Map<String, String> requestParams) {
        String userId = requestParams.get("userId");
        String orderNo = requestParams.get("orderNo");
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
