package org.neu.cs6650.koi.business.controller;

import org.neu.cs6650.koi.business.service.BusinessService;
import org.neu.cs6650.koi.common.dto.AccountDTO;
import org.neu.cs6650.koi.common.dto.BusinessDTO;
import org.neu.cs6650.koi.common.dto.CommodityDTO;
import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/orders")
    ObjectResponse handleCreateOrder(@RequestBody BusinessDTO businessDTO) {
        log.info("Request parameter: {}", businessDTO.toString());
        return businessService.handleCreateOrder(businessDTO);
    }

    @DeleteMapping("/orders")
    ObjectResponse deleteOrder(@RequestParam String order_no) {
        log.info("Delete order");
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(order_no);
        return businessService.deleteOrder(orderDTO);
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

    @PostMapping("/accounts/login")
    ObjectResponse handleLogin(@RequestBody AccountDTO accountDTO) {
        //LOGGER.info("Request parameter: {}", name, password);
        return businessService.handleLogin(accountDTO);
    }

    @PostMapping("/accounts/register")
    ObjectResponse handleRegister(@RequestBody AccountDTO accountDTO) {
        //LOGGER.info("Request parameter: {}", name, password);
        return businessService.handleRegister(accountDTO);
    }

    @GetMapping("/accounts")
    ObjectResponse handleGetAccount(String user_id) {
        //LOGGER.info("Request parameter: {}", user_name, password);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(user_id);
        return businessService.handleGetAccount(accountDTO);
    }
}
