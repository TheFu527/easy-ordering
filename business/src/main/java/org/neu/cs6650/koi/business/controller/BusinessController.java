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

//    @GetMapping("/getStock")
//    ObjectResponse getStock(@RequestParam String commodityCode) {
//        CommodityDTO commodityDTO = new CommodityDTO();
//        commodityDTO.setCommodityCode(commodityCode);
//        return businessService.getStock(commodityDTO);
//    }

//    @RequestMapping(value = "/stock", method = RequestMethod.GET)
//    ObjectResponse getStock(@RequestParam(name = "commodityCode") String commodityCode) {
//        CommodityDTO commodityDTO = new CommodityDTO();
//        commodityDTO.setCommodityCode(commodityCode);
//        return businessService.getStock(commodityDTO);
//    }


//    @GetMapping("/getAllStocks")
//    ObjectResponse getAllStock() {
//        log.info("Request All Stocks");
//        return businessService.getAllStocks();
//    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    ObjectResponse getAllStock() {
        log.info("Request All Stocks");
        return businessService.getAllStocks();
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    ObjectResponse getStock(@PathVariable(name = "commodityCode") String commodityCode) {
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(commodityCode);
        return businessService.getStock(commodityDTO);
    }

//    @GetMapping("/getOrderByOId")
//    ObjectResponse getOrderByOId(@RequestParam String orderNo) {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setOrderNo(orderNo);
//        return businessService.getOrderByOId(orderDTO);
//    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    ObjectResponse getOrderByOId(@RequestParam(name = "orderNo") String orderNo) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(orderNo);
        return businessService.getOrderByOId(orderDTO);
    }

//    @GetMapping("/getOrderByUId")
//    ObjectResponse getOrderByUId(@RequestParam String userId) {
//        AccountDTO accountDTO = new AccountDTO();
//        accountDTO.setUserId(userId);
//        return businessService.getOrderByUId(accountDTO);
//    }

    @RequestMapping(value = "/order/{userId}", method = RequestMethod.GET)
    ObjectResponse getOrderByUId(@PathVariable(name = "userId") String userId) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(userId);
        return businessService.getOrderByUId(accountDTO);
    }
}
