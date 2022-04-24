package org.neu.cs6650.koi.order.controller;

import org.neu.cs6650.koi.common.dto.OrderDTO;
import org.neu.cs6650.koi.common.response.ObjectResponse;
import org.neu.cs6650.koi.order.service.ITOrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class TOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TOrderController.class);

    @Autowired
    private ITOrderService orderService;

    @PostMapping("/create_order")
    ObjectResponse<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        LOGGER.info("createOrder: {}", orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }
}

